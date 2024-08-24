package com.stefick.zeljade.core.repository

import com.stefick.zeljade.core.api.CompendiumRemoteDataSource
import com.stefick.zeljade.core.mocks.CompendiumMocks
import com.stefick.zeljade.core.mocks.ExceptionMocks
import com.stefick.zeljade.core.dto.CompendiumDTO
import com.stefick.zeljade.core.network.base.ErrorResponse
import com.stefick.zeljade.core.network.base.NetworkResultBase
import com.stefick.zeljade.core.repository.Repository.Result
import kotlinx.coroutines.runBlocking
import okhttp3.Headers
import okio.IOException
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.HttpException
import java.util.concurrent.TimeoutException

@RunWith(JUnit4::class)
class CompendiumRepositoryTest {

    @Mock
    private lateinit var remoteDataSource: CompendiumRemoteDataSource

    private lateinit var repository: ICompendiumRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = CompendiumRepository(remoteDataSource)
    }


    @Test
    fun shouldSuccessfullyReturnCategoriesWhenRequestingBySpecificCategory() {

        val mockedCompendium = CompendiumMocks.getMockCompendium()

        val mockSuccessResponse = NetworkResultBase.Success(
            Headers.headersOf("key", "value"),
            mockedCompendium,
            200
        )

        runBlocking {
            Mockito.`when`(remoteDataSource.requestAllData())
                .thenReturn(mockSuccessResponse)

            repository.requestAllData().collect { result ->
                val response = result as Result.Success<*>
                assert(response.result is CompendiumDTO)
            }
        }
    }


    @Test
    fun shouldReturnAPIErrorWhenRequestTriggersNotFound() {

        val errorResponse = ErrorResponse(arrayListOf(), "Not found")
        val mockedURL = "http://compendium.zeljade.challenge/url"
        val code = 404

        val mockedNotFoundError = NetworkResultBase.ApiError(
            errorResponse,
            code,
            mockedURL
        )

        runBlocking {
            Mockito.`when`(remoteDataSource.requestAllData())
                .thenReturn(mockedNotFoundError)

            repository.requestAllData().collect { result ->
                val response = result as Result.Failed<*>

                assert(response.result is ErrorResponse)

                Assert.assertEquals(response.code, code)
                Assert.assertEquals(response.url, mockedURL)
            }
        }
    }


    @Test
    fun shouldReturnErrorWhenRequestFailsDueToAServerException() {
        val message = "Error while trying to load"
        val code = 500
        val mockedHttpException = ExceptionMocks.getMockedHttpException(message, code)

        val mockedFailResult = NetworkResultBase.NetworkError(
            mockedHttpException,
            code
        )

        runBlocking {
            Mockito.`when`(remoteDataSource.requestAllData())
                .then { mockedFailResult }

            repository.requestAllData().collect { result ->
                val response = result as Result.Error

                assert(response.response is NetworkResultBase<*, *>)
                assert((response.response as NetworkResultBase.NetworkError).error is HttpException)

                Assert.assertEquals(response.code, code)
            }
        }
    }


    @Test
    fun shouldReturnErrorWhenRequestTimesOut() {
        val message = "timeout"
        val code = 408
        val mockedTimeoutException = ExceptionMocks.getMockedTimeoutException(message)

        val mockedFailResult = NetworkResultBase.NetworkError(
            mockedTimeoutException,
            code
        )

        runBlocking {
            Mockito.`when`(remoteDataSource.requestAllData())
                .then { mockedFailResult }

            repository.requestAllData().collect { result ->
                val response = result as Result.Error

                assert(response.response is NetworkResultBase.NetworkError)
                assert((response.response as NetworkResultBase.NetworkError).error is TimeoutException)
                Assert.assertEquals(response.code, code)
            }
        }
    }


    @Test
    fun shouldReturnUnknownErrorWhenAnIOExceptionOccurs() {
        val message = "unknown"
        val code = -1
        val mockedUnknownException = ExceptionMocks.getMockedIOException(message)

        val mockedFailResult = NetworkResultBase.NetworkError(
            mockedUnknownException,
            code
        )

        runBlocking {
            Mockito.`when`(remoteDataSource.requestAllData())
                .then { mockedFailResult }

            repository.requestAllData().collect { result ->
                val response = result as Result.Error

                assert(response.response is NetworkResultBase.NetworkError)
                assert((response.response as NetworkResultBase.NetworkError).error is IOException)

                Assert.assertEquals(response.code, code)

            }
        }
    }

    @Test
    fun shouldReturnUnknownErrorWhenAnUnexpectedErrorOccurs() {
        val message = "unknown"
        val mockedURL = "http://compendium.zeljade.challenge/url"
        val mockedResponseBody = null
        val code = -1
        val mockedUnknownException = ExceptionMocks.getMockedUnknownException(message)

        val mockedFailResult = NetworkResultBase.Unknown(
            mockedUnknownException,
            code,
            mockedResponseBody,
            mockedURL
        )

        runBlocking {
            Mockito.`when`(remoteDataSource.requestAllData())
                .then { mockedFailResult }

            repository.requestAllData().collect { result ->
                val response = result as Result.Unknown

                assert(response.url.equals(mockedURL))

                Assert.assertEquals(response.code, code)
                Assert.assertEquals(response.responseBody, mockedResponseBody)
            }
        }
    }


}