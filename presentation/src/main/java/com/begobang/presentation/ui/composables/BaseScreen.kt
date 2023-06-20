package com.begobang.presentation.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun BaseScreen(loading: Boolean, error: String?, onRetry: () -> Unit, content: @Composable () -> Unit){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .size(size = 48.dp)

            )

        }

        error?.let {
            EmptyState(it) {
                onRetry()
            }
        }

        content()

    }
}