package com.begobang.domain

import arrow.core.Either
import com.begobang.domain.business.MarvelItemBusiness
import com.begobang.domain.failure.Failure
import com.begobang.domain.fakeRepository.FakeCharactersRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetCharactersTest {
    @Test
    fun `get characters use case - returns failure`() {
        //Given
        val response = Either.Left(Failure.BaseFailure())
        val repository = FakeCharactersRepository(response = response)
        val useCase = GetCharacters(repository)

        //When
        val actual = runBlocking { useCase.run(Unit) }

        //Then
        assertEquals(response, actual)
    }

    @Test
    fun `get characters use case - returns success`() {
        //Given
        val response = Either.Right(listOf<MarvelItemBusiness>())
        val repository = FakeCharactersRepository(response = response)
        val useCase = GetCharacters(repository)

        //When
        val actual = runBlocking { useCase.run(Unit) }

        //Then
        assertEquals(response, actual)
    }
}