package com.begobang.presentation.ui.screens.marvelList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.begobang.domain.GetCharacters
import com.begobang.domain.business.MarvelItemBusiness
import com.begobang.domain.failure.Failure
import com.begobang.presentation.ui.navigation.NavigationManager
import com.begobang.presentation.ui.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharacters: GetCharacters,
    private val navigationManager: NavigationManager
): ViewModel() {

    private val _state = MutableStateFlow(CharactersState())
    val state = _state.asStateFlow()

    init {
        getCharacters()
    }

    fun navigateToDetail(name: String){
        navigationManager.navigateDetail(Screens.MARVEL, name)
    }

    fun getCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = CharactersState(loading = true)
            getCharacters(Unit).fold(
                ::handleCharactersError,
                ::handleCharactersSuccess
            )
        }

    }

    private fun handleCharactersError(failure: Failure){
        _state.value = CharactersState(loading = false, error = failure.exception)
    }

    private fun handleCharactersSuccess(business: List<MarvelItemBusiness>?){
        _state.value = CharactersState(loading = false, characters = business)
    }

}


data class CharactersState(
    val loading: Boolean = false,
    val characters: List<MarvelItemBusiness>? = null,
    val error: String? = null
)