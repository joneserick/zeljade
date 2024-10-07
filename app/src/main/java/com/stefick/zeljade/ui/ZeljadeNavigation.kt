package com.stefick.zeljade.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

object ZeljadeDestinations {
    const val HOME_ROUTE = "home"
    const val CATEGORY_ROUTE = "category"
    const val ENTRY_ROUTE = "entry"
}
class ZeljadeNavigation(navController: NavController) {

    val navigateHome: () -> Unit = {
        navController.navigate(ZeljadeDestinations.HOME_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    val navigateCategory: () -> Unit = {
        navController.navigate(ZeljadeDestinations.CATEGORY_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    val navigateEntry: () -> Unit = {
        navController.navigate(ZeljadeDestinations.ENTRY_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

}