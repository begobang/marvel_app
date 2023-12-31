package com.begobang.data.remoteDataSource

import arrow.core.Either
import com.begobang.data.apiService.CharactersApiService
import com.begobang.data.apiService.requestGenericApi
import com.begobang.data.response.MarvelItemResponse
import com.begobang.data.response.toDomain
import com.begobang.domain.business.MarvelItemBusiness
import com.begobang.domain.failure.Failure
import retrofit2.await
import javax.inject.Inject

interface CharactersDataSource {
    suspend fun getCharacters(): Either<Failure, List<MarvelItemBusiness>?>
}

class CharactersRemoteDataSource @Inject constructor(
    private val service: CharactersApiService
): CharactersDataSource {
    override suspend fun getCharacters(): Either<Failure, List<MarvelItemBusiness>?> {
        return requestGenericApi(
            call = service.getCharactersAsync(),
            success = { it?.data?.results?.map { it.toDomain() } }
        )
    }
}