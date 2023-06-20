package com.begobang.presentation.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun EmptyState(message: String, onRetry: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(18.dp),
        verticalArrangement = Arrangement.Center) {
        Icon(
            imageVector = Icons.Default.Error,
            contentDescription = "Empty state",
            tint = Color.Red,
            modifier = Modifier
                .padding(top = 16.dp)
                .width(40.dp)
                .height(40.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(text = message, style = MaterialTheme.typography.h4, textAlign = TextAlign.Center)
        Icon(
            imageVector = Icons.Default.Refresh,
            contentDescription = "Refresh",
            tint = Color.DarkGray,
            modifier = Modifier
                .padding(top = 16.dp)
                .width(24.dp)
                .height(24.dp)
                .align(Alignment.CenterHorizontally)
                .clickable { onRetry() }
        )
    }
}

@Preview
@Composable
fun EmptyStatePreview(){
    EmptyState("Unable to saldasjdasñdajsds´´asjdñañ´´a" +
            "") {

    }
}