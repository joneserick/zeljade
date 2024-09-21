package com.stefick.zeljade.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.stefick.zeljade.ui.theme.ZeljadeTheme

@Composable
fun AppZeljade() {

    ZeljadeTheme {
        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            ZeljadeNavigation(navController)
        }
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute =
            navBackStackEntry?.destination?.route ?: ZeljadeDestinations.HOME_ROUTE
    }

}