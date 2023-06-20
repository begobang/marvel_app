package com.begobang.domain

import arrow.core.Either
import com.begobang.domain.failure.Failure

abstract class UseCase<out Type, in Params> where Type : Any? {
    abstract suspend fun run(params: Params? = null): Either<Failure, Type?>

    suspend operator fun invoke(
        params: Params? = null
    ): Either<Failure, Type?> {
        return run(params)
    }
}