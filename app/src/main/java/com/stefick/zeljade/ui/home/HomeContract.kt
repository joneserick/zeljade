package com.stefick.zeljade.ui.home

sealed class HomeContract {

    sealed class State {

        object Loading : State()

    }

}