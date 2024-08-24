package com.stefick.zeljade.features.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.stefick.zeljade.R
import com.stefick.zeljade.core.models.CompendiumModel
import com.stefick.zeljade.core.repository.ICompendiumRepository
import com.stefick.zeljade.features.home.HomeContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ICompendiumRepository
) : ViewModel() {

    private val _uiState: StateFlow<HomeContract.State> =
        MutableStateFlow(HomeContract.State.Loading)

    private val _compendium: MutableLiveData<CompendiumModel?> by lazy {
        MutableLiveData<CompendiumModel?>(null)
    }

    private val _error: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    val uiState: StateFlow<HomeContract.State> = _uiState
    val compendium: LiveData<CompendiumModel?> = _compendium
    val error: LiveData<Int> = _error

    fun requestAllData() {
        viewModelScope.launch {
            repository.requestAllData()
                .catch {
                    _error.value = R.string.default_error
                }
                .collect { result ->
                    when (result) {
                        null -> {
                            _error.value = R.string.default_error
                        }

                        else -> {
                            _compendium.value = result
                        }
                    }
                }
        }
    }

//    fun getSelectedSimpleCategoryItems(category: String?): ArrayList<CategoryResponse>? {
//        return when (category) {
//            CategoryEnum.EQUIPMENT.category -> categories.value?.data?.equipment
//            CategoryEnum.MATERIALS.category -> categories.value?.data?.materials
//            CategoryEnum.TREASURE.category -> categories.value?.data?.treasure
//            CategoryEnum.MONSTERS.category -> categories.value?.data?.monsters
//            else -> null
//        }
//    }

    class HomeViewModelFactory(private val repository: ICompendiumRepository) :
        ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(repository) as T
        }
    }
}