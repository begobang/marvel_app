package com.begobang.domain.failure

sealed class Failure(val exception: String? = null) {
    class BaseFailure(val code: String? = "0", message: String? = null): Failure(message)
}
