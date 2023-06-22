package com.begobang.presentation.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DetailCard(text: String, imageVector: ImageVector){
    Column(modifier = Modifier.padding(vertical = 10.dp)) {
       ElevatedCard {
           Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
              Icon(
                 imageVector = imageVector,
                  contentDescription = null
              )

               Text(text = text, modifier = Modifier.padding(start = 4.dp))
           }

       }
    }
}

@Composable
fun Category(title: String, modifier: Modifier){
    Text(
        text = title,
        modifier = modifier,
        style = MaterialTheme.typography.h2
    )
}

@Preview
@Composable
fun Prev(){
    DetailCard("Hola", Icons.Default.Tv)
}