package com.begobang.presentation.ui.screens.marvelDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.begobang.domain.GetCharacters
import com.begobang.domain.business.MarvelItemBusiness
import com.begobang.domain.failure.Failure
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getCharacters: GetCharacters
): ViewModel() {

    private val _state = MutableStateFlow(CharacterState())
    val state = _state.asStateFlow()

    private var characterId: Int = -1

    fun findCharacter(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = CharacterState(loading = true)
            characterId = id
            getCharacters(Unit).fold(
                ::handleCharacterError,
                ::handleCharacterSuccess
            )
        }
    }

    private fun handleCharacterError(failure: Failure){
        _state.value = CharacterState(loading = false, error = failure.exception)
    }

    private fun handleCharacterSuccess(business: List<MarvelItemBusiness>?){
        _state.value = CharacterState(loading = false, character = business?.find { it.id == characterId }, error = null)
    }
}

data class CharacterState(
    val loading: Boolean = false,
    val character: MarvelItemBusiness? = null,
    val error: String? = null
)