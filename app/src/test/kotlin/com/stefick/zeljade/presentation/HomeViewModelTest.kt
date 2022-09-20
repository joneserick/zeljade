package com.stefick.zeljade.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.stefick.zeljade.core.models.CategoriesResponse
import com.stefick.zeljade.core.models.CategoryResponse
import com.stefick.zeljade.core.models.CompendiumResponse
import com.stefick.zeljade.core.network.base.ErrorResponse
import com.stefick.zeljade.core.repository.ICompendiumRepository
import com.stefick.zeljade.core.repository.Repository
import com.stefick.zeljade.dispatchers.CoroutineDispatcherRule
import com.stefick.zeljade.features.home.models.CategoryCardItem
import com.stefick.zeljade.features.home.presentation.HomeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutinesDispatcherRule = CoroutineDispatcherRule()

    @Mock
    private lateinit var repository: ICompendiumRepository

    @Mock
    private lateinit var categories: Observer<CompendiumResponse>

    @Mock
    private lateinit var error: Observer<ErrorResponse>

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = HomeViewModel(repository)
    }

    @Test
    fun shouldRetrieveCompendiumDataSuccessfully() {
        val mockedResponse = Mocks.getMockCompendium()
        val code = 200

        val mockedResult = Repository.Result.Success(mockedResponse, code)
        val mockedFlow = flow { emit(mockedResult) }

        runTest {
            Mockito.`when`(repository.requestAllData()).thenReturn(mockedFlow)

            viewModel.categories.observeForever(categories)

            viewModel.requestAllData()

        }
        Mockito.verify(categories).onChanged(mockedResponse)
    }

    @Test
    fun shouldNotifyErrorObserverWhenAPIReturnsAnError() {

        val mockedMessage = "Not able to run"
        val url = "mocked url"
        val code = 408

        val mockedError = ErrorResponse(arrayListOf(Mocks.getMockCompendium()), mockedMessage)

        val mockedResult = Repository.Result.Failed(mockedError, code, url)
        val mockedFlow = flow { emit(mockedResult) }

        runTest {
            Mockito.`when`(repository.requestAllData()).thenReturn(mockedFlow)

            viewModel.error.observeForever(error)

            viewModel.requestAllData()

        }
        Mockito.verify(error).onChanged(mockedError)
    }

    @Test
    fun shouldReturnSimpleCategoriesByCategoryName() {
        val mockedCategory = "monsters"
        val mockedResponse = Mocks.getMockCompendium()
        val code = 200

        val mockedResult = Repository.Result.Success(mockedResponse, code)
        val mockedFlow = flow { emit(mockedResult) }

        runTest {
            Mockito.`when`(repository.requestAllData()).thenReturn(mockedFlow)
            viewModel.categories.observeForever(categories)
            viewModel.requestAllData()
        }

        val result = viewModel.getSelectedSimpleCategoryItems(mockedCategory)

        Assert.assertTrue(result?.isNotEmpty() == true)
        Assert.assertTrue(result?.any { it.category == mockedCategory } == true)

    }
    @Test
    fun shouldReturnNullWhenGetSimpleCategoriesByInvalidCategoryName() {
        val mockedCategory = "invalid-category"
        val mockedResponse = Mocks.getMockCompendium()
        val code = 200

        val mockedResult = Repository.Result.Success(mockedResponse, code)
        val mockedFlow = flow { emit(mockedResult) }

        runTest {
            Mockito.`when`(repository.requestAllData()).thenReturn(mockedFlow)
            viewModel.categories.observeForever(categories)
            viewModel.requestAllData()
        }

        val result = viewModel.getSelectedSimpleCategoryItems(mockedCategory)

        Assert.assertTrue(result == null)
    }
}