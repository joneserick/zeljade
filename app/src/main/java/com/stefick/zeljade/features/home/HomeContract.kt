package com.stefick.zeljade.features.home

sealed class HomeContract {

    sealed class State {

        object Loading : State()

    }

}