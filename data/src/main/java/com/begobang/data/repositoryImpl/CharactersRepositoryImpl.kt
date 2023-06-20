package com.begobang.data.repositoryImpl

import arrow.core.Either
import com.begobang.data.remoteDataSource.CharactersDataSource
import com.begobang.domain.CharactersRepository
import com.begobang.domain.business.MarvelItemBusiness
import com.begobang.domain.failure.Failure
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val remoteDataSource: CharactersDataSource
): CharactersRepository {
    override suspend fun getCharacters(): Either<Failure, List<MarvelItemBusiness>?> {
        return remoteDataSource.getCharacters()
    }

    override suspend fun getCharacter(id: Int): Either<Failure, MarvelItemBusiness?> {
        return remoteDataSource.findCharacter(id)
    }
}