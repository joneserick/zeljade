package com.stefick.zeljade.core.repository

import com.stefick.zeljade.core.api.CompendiumRemoteDataSource
import com.stefick.zeljade.core.mocks.CategoryMocks
import com.stefick.zeljade.core.mocks.ExceptionMocks
import com.stefick.zeljade.core.models.CategoryItemResponse
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
    fun shouldReturnCategoriesWhenRequestingBySpecifCategory() {
        val category = "monsters"
        val categoryItemResponse = CategoryMocks.getMockCategory(category)

        val mockSuccessResponse = NetworkResultBase.Success(
            Headers.headersOf("key", "value"),
            categoryItemResponse,
            200
        )

        runBlocking {
            Mockito.`when`(remoteDataSource.requestDataByCategory(category))
                .thenReturn(mockSuccessResponse)

            repository.requestDataByCategory(category).collect { result ->
                val response = result as Result.Success<*>
                assert(response.result is CategoryItemResponse)
                Assert.assertEquals(
                    (response.result as CategoryItemResponse).data.first().category,
                    category
                )
            }
        }
    }


    @Test
    fun shouldReturnAPIErrorWhenRequestTriggersNotFound() {
        val category = "monsters"
        val errorResponse = ErrorResponse(listOf(), "Not found")
        val mockedURL = "http://compendium.zeljade.challenge/url"
        val code = 404

        val mockedNotFoundError = NetworkResultBase.ApiError(
            errorResponse,
            code,
            mockedURL
        )

        runBlocking {
            Mockito.`when`(remoteDataSource.requestDataByCategory(category))
                .thenReturn(mockedNotFoundError)

            repository.requestDataByCategory(category).collect { result ->
                val response = result as Result.Failed<*>

                Assert.assertTrue(response.result is ErrorResponse)
                Assert.assertEquals(response.code, code)
                Assert.assertEquals(response.url, mockedURL)
            }
        }
    }


    @Test
    fun shouldReturnErrorWhenRequestFailsDueToAnException() {
        val category = "monsters"
        val message = "Error while trying to load"
        val code = 500
        val mockedHttpException = ExceptionMocks.getMockedHttpException(message, code)

        val mockedFailResult = NetworkResultBase.NetworkError(
            mockedHttpException,
            code
        )

        runBlocking {
            Mockito.`when`(remoteDataSource.requestDataByCategory(category))
                .then { mockedFailResult }

            repository.requestDataByCategory(category).collect { result ->
                val response = result as Result.Error

                Assert.assertTrue(response.response is NetworkResultBase<*, *>)
                Assert.assertTrue((response.response as NetworkResultBase.NetworkError).error is HttpException)
                Assert.assertEquals(response.code, code)
            }
        }
    }


    @Test
    fun shouldReturnErrorWhenRequestTimesOut() {
        val category = "monsters"
        val message = "timeout"
        val code = 408
        val mockedTimeoutException = ExceptionMocks.getMockedTimeoutException(message)

        val mockedFailResult = NetworkResultBase.NetworkError(
            mockedTimeoutException,
            code
        )

        runBlocking {
            Mockito.`when`(remoteDataSource.requestDataByCategory(category))
                .then { mockedFailResult }

            repository.requestDataByCategory(category).collect { result ->
                val response = result as Result.Error
                Assert.assertTrue(response.response is NetworkResultBase.NetworkError)
                Assert.assertTrue((response.response as NetworkResultBase.NetworkError).error is TimeoutException)
                Assert.assertEquals(response.code, code)
            }
        }
    }


    @Test
    fun shouldReturnUnknownErrorWhenAnIOExceptionOccurs() {
        val category = "monsters"
        val message = "unknown"
        val code = -1
        val mockedUnknownException = ExceptionMocks.getMockedIOException(message)

        val mockedFailResult = NetworkResultBase.NetworkError(
            mockedUnknownException,
            code
        )

        runBlocking {
            Mockito.`when`(remoteDataSource.requestDataByCategory(category))
                .then { mockedFailResult }

            repository.requestDataByCategory(category).collect { result ->
                val response = result as Result.Error
                Assert.assertTrue(response.response is NetworkResultBase.NetworkError)
                Assert.assertTrue((response.response as NetworkResultBase.NetworkError).error is IOException)
                Assert.assertEquals(response.code, code)

            }
        }
    }

    @Test
    fun shouldReturnUnknownErrorWhenAnUnexpectedErrorOccurs() {
        val category = "monsters"
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
            Mockito.`when`(remoteDataSource.requestDataByCategory(category))
                .then { mockedFailResult }

            repository.requestDataByCategory(category).collect { result ->
                val response = result as Result.Unknown

                Assert.assertTrue(response.url.equals(mockedURL))
                Assert.assertEquals(response.code, code)
                Assert.assertEquals(response.responseBody, mockedResponseBody)
            }
        }
    }


}