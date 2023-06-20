package com.begobang.data.fakeDataSource

import arrow.core.Either
import com.begobang.data.remoteDataSource.CharactersDataSource
import com.begobang.domain.business.MarvelItemBusiness
import com.begobang.domain.failure.Failure

class FakeCharactersRemoteDataSource(private val response: Either<Failure, List<MarvelItemBusiness>?>? = null, private val single: Either<Failure, MarvelItemBusiness?>? = null): CharactersDataSource {

    override suspend fun getCharacters(): Either<Failure, List<MarvelItemBusiness>?> {
        return response!!

    }

    override suspend fun findCharacter(id: Int): Either<Failure, MarvelItemBusiness?> {
        return single!!
    }


}