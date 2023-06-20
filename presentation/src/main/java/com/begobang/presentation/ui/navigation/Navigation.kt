package com.begobang.presentation.ui.navigation

import android.util.Log
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.begobang.presentation.ui.screens.marvelList.CharactersList

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.MARVEL.route
    ) {
        marvelNav(navController)
    }
}

private fun NavGraphBuilder.marvelNav(navController: NavController) {
    navigation(
        startDestination = NavCommand.ContentType(Screens.MARVEL).route,
        route = Screens.MARVEL.route
    ) {
        composable(NavCommand.ContentType(Screens.MARVEL)) {
            CharactersList()
        }

        composable(NavCommand.ContentTypeDetail(Screens.MARVEL)) {
            it.arguments?.getString("itemId")?.let { itemId ->  Text(text = itemId) }
        }
    }
}

private fun NavGraphBuilder.composable(
    navCommand: NavCommand,
    content: @Composable (NavBackStackEntry) -> Unit,
) {
    composable(
        route = navCommand.route,
        arguments = navCommand.args
    ) {
        content(it)
        Log.d("nav command", navCommand.args.toString())
    }
}