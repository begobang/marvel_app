package com.begobang.domain

import arrow.core.Either
import com.begobang.domain.business.MarvelItemBusiness
import com.begobang.domain.failure.Failure
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCharacter @Inject constructor(
    private val repository: CharactersRepository
): UseCase<MarvelItemBusiness, Int>() {
    override suspend fun run(params: Int?): Either<Failure, MarvelItemBusiness?> {
        return repository.getCharacter(params ?: 0)
    }
}