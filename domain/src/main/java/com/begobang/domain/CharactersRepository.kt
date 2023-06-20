package com.begobang.domain

import arrow.core.Either
import com.begobang.domain.business.MarvelItemBusiness
import com.begobang.domain.failure.Failure

interface CharactersRepository {
    suspend fun getCharacters(): Either<Failure, List<MarvelItemBusiness>?>

    suspend fun getCharacter(id: Int): Either<Failure, MarvelItemBusiness?>
}