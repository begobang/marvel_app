package com.begobang.data.response

data class ApiResponse<T>(
    val code: Int?,
    val status: String?,
    val copyright: String?,
    val attributionText: String?,
    val attributionHTML: String?,
    val etag: String?,
    val data: ApiData<T>?
)

data class ApiData<T>(
    val offset: Int?,
    val limit: Int?,
    val total: Int?,
    val count: Int?,
    val results: List<T>?
)
