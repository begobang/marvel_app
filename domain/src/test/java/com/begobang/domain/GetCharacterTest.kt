package com.begobang.domain

import arrow.core.Either
import com.begobang.domain.business.MarvelItemBusiness
import com.begobang.domain.failure.Failure
import com.begobang.domain.fakeRepository.FakeCharactersRepository
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetCharacterTest {

    private val item = MarvelItemBusiness(1, "test", null, null, null, null, null, null, null, null, null)

    @Test
    fun `get characters use case - returns failure`() {
        //Given
        val response = Either.Left(Failure.BaseFailure())
        val repository = FakeCharactersRepository(single = response)
        val useCase = GetCharacter(repository)

        //When
        val actual = runBlocking { useCase.run(1) }

        //Then
        TestCase.assertEquals(response, actual)
    }

    @Test
    fun `get characters use case - returns success`() {
        //Given
        val response = Either.Right(item)
        val repository = FakeCharactersRepository(single = response)
        val useCase = GetCharacter(repository)

        //When
        val actual = runBlocking { useCase.run(1) }

        //Then
        TestCase.assertEquals(response, actual)
    }
}