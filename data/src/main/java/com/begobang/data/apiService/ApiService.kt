package com.begobang.data.apiService

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.begobang.domain.failure.Failure
import retrofit2.Call
import retrofit2.Response

suspend fun <Api, Domain> requestGenericApi(
    call: Call<Api>,
    success: (Api?) -> Domain?
): Either<Failure, Domain?> {
    return Either.catch {
        call
    }.fold(
        { error ->
            manageException(error)
        },
        { response ->
            val res = response.execute()
            parseGenericResponse(res, success)
        }
    )

}

private fun <Domain> manageException(error: Throwable): Either<Failure, Domain> {
    return Failure.BaseFailure(message = error.message).left()
}

private fun <Api, Domain> parseGenericResponse(
    response: Response<Api>,
    success: (Api?) -> Domain
): Either<Failure, Domain> {
    return if(response.isSuccessful) {
        success(response.body()).right()
    } else {
        Either.left(Failure.BaseFailure(message = "Unknown Error"))
    }
}