package com.begobang.presentation.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.begobang.presentation.ui.theme.MarvelTheme

@Composable
fun SearchBar(search: String, onValueChange: (String) -> Unit, onReset: () -> Unit, onRetry: () -> Unit){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        TextField(
            value = search,
            onValueChange = { onValueChange(it) },
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .background(Color.Transparent),
            shape = MaterialTheme.shapes.medium,
            colors = TextFieldDefaults.textFieldColors(focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent),
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = { onReset() }) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = "delete")

                }
            }
        )
        Icon(
            imageVector = Icons.Default.Refresh,
            contentDescription = "refresh",
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(12.dp)
                .size(36.dp)
                .clickable { onRetry() },
            tint = MaterialTheme.colors.secondary
        )

    }

}

@Preview
@Composable
fun SearchBarPreview(){
    MarvelTheme {
        SearchBar("", {}, {}, {})
    }
}