package com.begobang.presentation.ui.navigation

import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow

class NavigationManager {
    private var commands = MutableStateFlow<NavCommand>(NavCommand.ContentType(Screens.MARVEL))

    var navController: NavController? = null

    fun navigate(
        screens: Screens
    ) {
        commands.value = NavCommand.ContentTypeDetail(screens)
        navController?.navigate((commands.value as NavCommand.ContentTypeDetail).createRoute())
    }

    fun navigateDetail(
        screens: Screens,
        itemId: String
    ){
        commands.value = NavCommand.ContentTypeDetail(screens)
        navController?.navigate((commands.value as NavCommand.ContentTypeDetail).createRoute(itemId))
    }
}