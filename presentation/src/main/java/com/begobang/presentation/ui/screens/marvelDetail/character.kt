package com.begobang.presentation.ui.screens.marvelDetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.PartyMode
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.begobang.domain.business.MarvelItemBusiness
import com.begobang.presentation.ui.composables.BaseScreen
import com.begobang.presentation.ui.composables.Category
import com.begobang.presentation.ui.composables.DetailCard

@Composable
fun Character(id: Int, viewModel: CharacterViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()

    viewModel.findCharacter(id)

    CharacterScreen(loading = state.loading, character = state.character, error = state.error) {
        viewModel.findCharacter(id)
    }


}

@Composable
fun CharacterScreen(loading: Boolean, character: MarvelItemBusiness?, error: String? = null, onRetry: () -> Unit){
    BaseScreen(loading = loading, error = error, onRetry = { onRetry() }) {
        if(character != null){
            Box {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("${character.thumbnail?.path}.${character.thumbnail?.extension}")
                        .crossfade(true)
                        .build(),
                    contentDescription = character.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f)
                        .padding(bottom = 30.dp)
                )


            }

            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                item {
                    Category(title = "Comics", modifier = Modifier)
                }
                items(items = character.comics?.items ?: emptyList()) {
                    DetailCard(text = it.name?: "", imageVector = Icons.Default.Book)
                }

                item {
                    Category(title = "Series", modifier = Modifier)
                }

                items(items = character.series?.items ?: emptyList()) {
                    DetailCard(text = it.name?: "", imageVector = Icons.Default.Tv)
                }

                item {
                    Category(title = "Stories", modifier = Modifier)
                }

                items(items = character.stories?.items ?: emptyList()) {
                    DetailCard(text = it.name?: "", imageVector = Icons.Default.History)
                }

                item {
                    Category(title = "Events", modifier = Modifier)
                }

                items(items = character.events?.items ?: emptyList()) {
                    DetailCard(text = it.name?: "", imageVector = Icons.Default.PartyMode)
                }
            }
        }

    }
}

