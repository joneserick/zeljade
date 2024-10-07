package com.stefick.zeljade.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stefick.zeljade.domain.usecases.GetEntireCompendiumUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val compendiumUseCase: GetEntireCompendiumUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val state: StateFlow<UiState> = _uiState.asStateFlow()

    private fun onRequestAllCompendiumData() {
        viewModelScope.launch {
            compendiumUseCase.getCompendium().collect { response ->
                response?.let { compendium ->
                    _uiState.update {
                        UiState.AllCategories(compendium)
                    }
                }
            }
        }
    }

}