package com.stefick.zeljade.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeRoute(modifier: Modifier = Modifier) {
    //collect ui state with lifecycle
    //check uiState to see which type of home we are dealing with
    //And create different components to handle them

    HomeScreen(modifier = modifier)

}