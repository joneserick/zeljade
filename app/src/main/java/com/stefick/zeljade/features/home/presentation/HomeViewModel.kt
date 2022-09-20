package com.stefick.zeljade.features.home.presentation

import android.widget.ArrayAdapter
import androidx.lifecycle.*
import com.stefick.zeljade.core.models.*
import com.stefick.zeljade.core.network.base.ErrorResponse
import com.stefick.zeljade.core.repository.ICompendiumRepository
import com.stefick.zeljade.core.repository.Repository
import com.stefick.zeljade.features.home.models.CategoryCardItem
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: ICompendiumRepository
) : ViewModel(), LifecycleObserver {

    private val _categories: MutableLiveData<CompendiumResponse> by lazy {
        MutableLiveData<CompendiumResponse>()
    }

    private val _error: MutableLiveData<ErrorResponse> by lazy {
        MutableLiveData<ErrorResponse>()
    }

    val categories: LiveData<CompendiumResponse> = _categories
    val error: LiveData<ErrorResponse> = _error

    fun requestAllData() {
        viewModelScope.launch {
            repository.requestAllData()
                .collect { result ->
                    when (result) {
                        is Repository.Result.Success<*> -> _categories.value =
                            (result.result as CompendiumResponse)
                        is Repository.Result.Failed<*> -> _error.value =
                            result.result as ErrorResponse
                        is Repository.Result.Unknown -> {
                            _error.value = ErrorResponse(null, "Unknown Error")
                        }
                        else -> _error.value = ErrorResponse(null, "Unknown Error")

                    }
                }
        }
    }

    fun getSelectedSimpleCategoryItems(category: String?): ArrayList<CategoryResponse>? {
        return when (category) {
            CategoryEnum.EQUIPMENT.category -> categories.value?.data?.equipment
            CategoryEnum.MATERIALS.category -> categories.value?.data?.materials
            CategoryEnum.TREASURE.category -> categories.value?.data?.treasure
            CategoryEnum.MONSTERS.category -> categories.value?.data?.monsters
            else -> null
        }
    }

    class HomeViewModelFactory(private val repository: ICompendiumRepository) :
        ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(repository) as T
        }
    }
}