package com.begobang.data.repositoryImpl

import arrow.core.Either
import com.begobang.data.fakeDataSource.FakeCharactersRemoteDataSource
import com.begobang.domain.business.MarvelItemBusiness
import com.begobang.domain.failure.Failure
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CharactersRepositoryImplTest {

    private val response: List<MarvelItemBusiness> = emptyList()

    @Test
    fun `get characters - returns success`() {
        //Given
        val either = Either.Right(response)
        val remoteDataSource = FakeCharactersRemoteDataSource(response = either)
        val repository = CharactersRepositoryImpl(remoteDataSource)

        //When
        val actual = runBlocking { repository.getCharacters() }

        //Then
        TestCase.assertEquals(either, actual)
    }

    @Test
    fun `get characters - returns failure`() {
        //Given
        val either = Either.Left(Failure.BaseFailure())
        val remoteDataSource = FakeCharactersRemoteDataSource(response = either)
        val repository = CharactersRepositoryImpl(remoteDataSource)

        //When
        val actual = runBlocking { repository.getCharacters() }

        //Then
        TestCase.assertEquals(either, actual)
    }
}