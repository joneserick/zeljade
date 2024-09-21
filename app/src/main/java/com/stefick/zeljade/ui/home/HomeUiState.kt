package com.stefick.zeljade.ui.home

sealed interface HomeUiState {

    val isLoading: Boolean

    data class Start(
        override val isLoading: Boolean
    ) : HomeUiState


}