package com.stefick.zeljade.features.home

sealed class HomeContract {

    sealed class State {

        object Loading : State()

        //Need to pass the data that the UI can understand,
        //if the domain changes it should not affect the expected data


    }

}