package com.begobang.presentation.ui.composables


import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import java.util.Locale

@Composable
fun NameLabel(name: String?, style: TextStyle = MaterialTheme.typography.h2, modifier: Modifier = Modifier){
    Box {
        Text(
            text = name?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
                ?: "",
            style = style,
            modifier = modifier.align(Alignment.Center)
        )
    }

}

@Preview
@Composable
fun NameTest(){
    NameLabel(name = "Superman")
}