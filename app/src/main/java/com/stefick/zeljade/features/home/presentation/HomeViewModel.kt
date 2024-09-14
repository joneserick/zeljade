package com.stefick.zeljade.features.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.stefick.zeljade.R
import com.stefick.zeljade.core.models.CategoryModel
import com.stefick.zeljade.core.models.CompendiumEntryModel
import com.stefick.zeljade.core.models.CompendiumModel
import com.stefick.zeljade.core.repository.ICompendiumRepository
import com.stefick.zeljade.features.home.HomeContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private val _entry: MutableStateFlow<CompendiumEntryModel?> = MutableStateFlow(null)
    val entry: StateFlow<CompendiumEntryModel?> = _entry.asStateFlow()

    private val _category: MutableStateFlow<CategoryModel?> = MutableStateFlow(null)
    val category: StateFlow<CategoryModel?> = _category.asStateFlow()

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

    fun requestEntryData(entryId: String) {
        viewModelScope.launch {
            repository.requestEntryData(entryId).collect { result ->
                when (result) {
                    null -> Unit
                    else -> {
                        _entry.value = result
                    }
                }
            }
        }
    }

    fun requestCategoryData(categoryName: CharSequence) {
        viewModelScope.launch {
            repository.requestCategoryData(categoryName).collect { result ->
                when (result) {
                    null -> Unit
                    else -> {
                        _category.value = result
                    }
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