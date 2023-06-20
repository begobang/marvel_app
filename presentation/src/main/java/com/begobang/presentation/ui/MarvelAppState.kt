package com.begobang.presentation.ui

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.begobang.presentation.ui.navigation.NavigationItem

@Composable
fun rememberPokemonAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController()
): MarvelAppState = remember(scaffoldState, navController) {
    MarvelAppState(scaffoldState, navController)
}

class MarvelAppState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController
) {

    companion object{
        val BOTTOM_NAV_OPTIONS = listOf(NavigationItem.MARVEL)
    }

    fun onNavItemClick(navItem: NavigationItem) {
        navController.navigatePoppingUpToStartDestination(navItem.navCommand.route)
    }

    fun onUpClick() {
        navController.popBackStack()
    }

    val currentScreen: Int?
        @Composable get() = BOTTOM_NAV_OPTIONS.find {
            it.navCommand.route == currentRoute
        }?.title

    val currentRoute: String
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination?.route
            ?: ""

    val showUpNavigation: Boolean
        @Composable get() = !NavigationItem.values().map { it.navCommand.route }.contains(currentRoute)
}

fun NavHostController.navigatePoppingUpToStartDestination(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}