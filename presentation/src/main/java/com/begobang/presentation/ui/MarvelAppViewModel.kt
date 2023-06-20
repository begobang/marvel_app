package com.begobang.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.begobang.presentation.ui.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MarvelAppViewModel @Inject constructor(
    private val navigationManager: NavigationManager
): ViewModel() {

    fun setNavController(navController: NavController){
        navigationManager.navController = navController
    }

}