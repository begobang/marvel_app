package com.begobang.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.begobang.presentation.R
import com.begobang.presentation.ui.composables.Toolbar
import com.begobang.presentation.ui.navigation.Navigation
import com.begobang.presentation.ui.theme.MarvelTheme

@Composable
fun MarvelApp(appState: MarvelAppState = rememberMarvelAppState(), viewModel: MarvelAppViewModel = hiltViewModel()) {

    viewModel.setNavController(appState.navController)

    MarvelScreen {
        Toolbar(
            toolbarName = stringResource(id = if(appState.showUpNavigation) R.string.detail else R.string.app_name),
            showUpNavigation = appState.showUpNavigation,
            onUpClick = { appState.onUpClick() }
        ) {
            Column {
                Navigation(appState.navController)
            }
        }
    }
}

@Composable
fun MarvelScreen(content: @Composable () -> Unit){
    MarvelTheme {
       Surface(color = MaterialTheme.colors.background) {
          content() 
       } 
    }
}


