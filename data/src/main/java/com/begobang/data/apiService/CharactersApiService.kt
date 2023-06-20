package com.begobang.data.apiService

import com.begobang.data.response.ApiResponse
import com.begobang.data.response.MarvelItemResponse
import retrofit2.Call
import retrofit2.http.GET

interface CharactersApiService {

    @GET("/characters")
    fun getCharactersAsync(): Call<ApiResponse<MarvelItemResponse>>
}