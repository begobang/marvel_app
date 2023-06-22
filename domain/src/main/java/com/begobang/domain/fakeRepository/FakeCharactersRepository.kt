package com.begobang.domain.fakeRepository

import arrow.core.Either
import com.begobang.domain.CharactersRepository
import com.begobang.domain.business.MarvelItemBusiness
import com.begobang.domain.failure.Failure

class FakeCharactersRepository(private val response: Either<Failure, List<MarvelItemBusiness>?>? = null): CharactersRepository {
    override suspend fun getCharacters(): Either<Failure, List<MarvelItemBusiness>?> {
        return response!!
    }
}