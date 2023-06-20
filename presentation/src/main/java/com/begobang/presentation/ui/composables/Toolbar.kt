package com.begobang.presentation.ui.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.begobang.presentation.ui.MarvelScreen

@Composable
fun Toolbar(
    toolbarName: String,
    showUpNavigation: Boolean,
    onUpClick: () -> Unit,
    content: @Composable (padding: PaddingValues) -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = toolbarName,
                        style = MaterialTheme.typography.h1
                    )
                },
                navigationIcon = {
                    if(showUpNavigation){
                        AppBarIcon(
                            imageVector = Icons.Default.ArrowBack,
                            onClick = { onUpClick() },
                            contentDescription = "back"
                        )
                    }
                }
            )
        }
    ) {
        content(it)
    }
}

@Composable
fun AppBarIcon(imageVector: ImageVector, onClick: () -> Unit, contentDescription: String? = null) {
    IconButton(onClick = onClick) {
        Icon(imageVector = imageVector, contentDescription = contentDescription)
    }
}

@Preview
@Composable
fun ToolbarPreview() {
    MarvelScreen {
        Toolbar(toolbarName = "Hello world", showUpNavigation = true, {}) {}
    }
}