package com.stefick.zeljade.features.home.presentation

import androidx.lifecycle.*
import com.stefick.zeljade.R
import com.stefick.zeljade.core.models.CategoryEnum
import com.stefick.zeljade.core.models.CategoryResponse
import com.stefick.zeljade.core.models.CompendiumResponse
import com.stefick.zeljade.core.network.base.ErrorResponse
import com.stefick.zeljade.core.repository.ICompendiumRepository
import com.stefick.zeljade.core.repository.Repository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: ICompendiumRepository
) : ViewModel() {

    private val _categories: MutableLiveData<CompendiumResponse> by lazy {
        MutableLiveData<CompendiumResponse>()
    }

    private val _error: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    val categories: LiveData<CompendiumResponse> = _categories
    val error: LiveData<Int> = _error

    fun requestAllData() {
        viewModelScope.launch {
            repository.requestAllData()
                .collect { result ->
                    when (result) {
                        is Repository.Result.Success<*> -> _categories.value =
                            (result.result as CompendiumResponse)
                        is Repository.Result.Failed<*> -> _error.value = R.string.timeout_error_message
                        is Repository.Result.Unknown -> {
                                _error.value = R.string.default_error
                        }
                        else -> _error.value = R.string.unknown_error


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