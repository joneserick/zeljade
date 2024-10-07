package com.stefick.zeljade.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.stefick.zeljade.ZeljadeApplication
import com.stefick.zeljade.ZeljadeApplication.Companion.ZELJADE_APP_URI
import com.stefick.zeljade.ui.home.HomeRoute

@Composable
fun ZeljadeNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ZeljadeDestinations.HOME_ROUTE
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(
            route = ZeljadeDestinations.HOME_ROUTE,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "$ZELJADE_APP_URI/${ZeljadeDestinations.HOME_ROUTE}"
                }
            )
        ) {
            // viewmodel Instance
            HomeRoute()
        }
    }

}