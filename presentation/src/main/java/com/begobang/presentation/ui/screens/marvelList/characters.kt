package com.begobang.presentation.ui.screens.marvelList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.begobang.domain.business.MarvelItemBusiness
import com.begobang.presentation.R
import com.begobang.presentation.ui.composables.BaseScreen
import com.begobang.presentation.ui.composables.NameLabel
import com.begobang.presentation.ui.composables.SearchBar
import com.begobang.presentation.ui.composables.Separator

@Composable
fun CharactersList(viewModel: CharactersViewModel = hiltViewModel()){

    val state by viewModel.state.collectAsState()

    CharactersScreen(
        loading = state.loading,
        characters = state.characters,
        error = state.error,
        onClick = {
            viewModel.navigateToDetail(it)
        }
    ){
        viewModel.getCharacters()
    }
}

@Composable
fun CharactersScreen(loading: Boolean, characters: List<MarvelItemBusiness>? = emptyList(), error: String? = null, onClick: (String) -> Unit, onRetry: () -> Unit) {

    var search by rememberSaveable { mutableStateOf("") }

    BaseScreen(loading = loading, error = error, onRetry = { onRetry() }) {
        characters?.let { list ->
            if(list.isNotEmpty()){
                SearchBar(
                    search = search,
                    onValueChange = { search = it },
                    onReset = { search = "" }) { onRetry() }

                LazyColumn(
                    contentPadding = PaddingValues(16.dp)) {
                    items(items = list.filter { it.name?.contains(search, true) == true } ) {
                        CharacterItemScreen(
                            item = it,
                            modifier = Modifier.clickable {
                                onClick(it.name ?: "")
                            }
                        )
                    }
                }

            }
        }
    }

}


@Composable
fun CharacterItemScreen(
    item: MarvelItemBusiness,
    modifier: Modifier
){

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp, top = 4.dp)
    ){
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
            ){
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("${item.thumbnail?.path}.${item.thumbnail?.extension}")
                    .crossfade(500)
                    .build(),
                contentDescription = item.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth(0.35f)
                    .height(70.dp)
                    .clip(MaterialTheme.shapes.medium),
                placeholder = painterResource(R.drawable.ic_marvel),
                error = painterResource(R.drawable.ic_marvel)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 14.dp),
                verticalArrangement = Arrangement.aligned(Alignment.CenterVertically)
            ){
                NameLabel(
                    name = item.name, modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
            }


        }

        Separator(color = Color.LightGray)
    }


}

@Preview
@Composable
fun ListPreview(){
    CharactersScreen(loading = false, error = "Unable to resolve host.", onClick =  {}, onRetry = {})
}

