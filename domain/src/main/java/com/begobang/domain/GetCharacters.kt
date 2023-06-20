package com.begobang.domain

import arrow.core.Either
import com.begobang.domain.business.MarvelItemBusiness
import com.begobang.domain.failure.Failure
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCharacters @Inject constructor(
    private val repository: CharactersRepository
) : UseCase<List<MarvelItemBusiness>, Unit>() {
    override suspend fun run(params: Unit?): Either<Failure, List<MarvelItemBusiness>?> {
        return repository.getCharacters()
    }

}