package com.stefick.zeljade.features.home.presentation

import androidx.lifecycle.*
import com.stefick.zeljade.core.models.CategoryItemResponse
import com.stefick.zeljade.core.network.base.ErrorResponse
import com.stefick.zeljade.core.repository.ICompendiumRepository
import com.stefick.zeljade.core.repository.Repository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: ICompendiumRepository
) : ViewModel(), LifecycleObserver {

    private val _categories: MutableLiveData<CategoryItemResponse> by lazy {
        MutableLiveData<CategoryItemResponse>()
    }

    private val _error: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val categories: LiveData<CategoryItemResponse> = _categories
    val error: LiveData<String> = _error

    fun requestDataByCategory(category: String) {
        viewModelScope.launch {
            repository.requestDataByCategory(category)
                .collect { result ->
                    when (result) {
                        is Repository.Result.Success<*> -> _categories.postValue(result.result as CategoryItemResponse)
                        is Repository.Result.Failed<*> -> _error.postValue((result.result as ErrorResponse).message)
                        is Repository.Result.Unknown -> _error.postValue("should improve error response: ${result.url}")
                        else -> _error.postValue("default error value")
                    }
                }
        }
    }

    class HomeViewModelFactory(private val repository: ICompendiumRepository) :
        ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(repository) as T
        }
    }

}