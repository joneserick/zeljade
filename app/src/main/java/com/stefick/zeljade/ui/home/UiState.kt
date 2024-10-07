package com.stefick.zeljade.ui.home

import com.stefick.zeljade.core.models.CompendiumModel

sealed class UiState {

    object Loading: UiState()
    data class AllCategories(val compendium: CompendiumModel): UiState()
    object Error: UiState()

}
