package com.stefick.zeljade.presentation

import com.stefick.zeljade.core.network.base.ErrorResponse
import com.stefick.zeljade.core.repository.ICompendiumRepository
import com.stefick.zeljade.core.repository.Repository
import com.stefick.zeljade.dispatchers.CoroutineDispatcherRule
import com.stefick.zeljade.features.home.presentation.item_details.EntryPresenter
import com.stefick.zeljade.features.home.presentation.item_details.EntryViewContract
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class EntryPresenterTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutinesDispatcherRule = CoroutineDispatcherRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    private lateinit var scope: TestScope

    @Mock
    private lateinit var repository: ICompendiumRepository

    @Mock
    private lateinit var view: EntryViewContract

    private lateinit var presenter: EntryPresenter

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        scope = TestScope()
        presenter = EntryPresenter(view, repository, scope)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun shouldRequestSingleEntryById() {
        val mockedResponse = Mocks.getMockEntryResponse()
        val mockedEntry = mockedResponse.data?.id ?: 0
        val code = 200

        val mockedResult = Repository.Result.Success(mockedResponse, code)
        val mockedFlow = flow { emit(mockedResult) }

        runTest {
            Mockito.`when`(repository.requestEntryData(mockedEntry)).thenReturn(mockedFlow)
            presenter.getEntry(mockedEntry)
        }

        Mockito.verify(view).displayEntry(mockedResponse)
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun shouldDisplayErrorWhenSingleEntryRequestFails() {
        val mockedEntry = 0
        val mockedResponse = ErrorResponse(arrayListOf(), "Not found")
        val code = 404

        val mockedResult = Repository.Result.Failed(mockedResponse, code)
        val mockedErrorFlow = flow { emit(mockedResult) }

        runTest {
            Mockito.`when`(repository.requestEntryData(mockedEntry)).thenReturn(mockedErrorFlow)
            presenter.getEntry(mockedEntry)
        }

        Mockito.verify(view).displayError(mockedResponse)
    }

}