package com.screen.doorgelezen.data.api

import com.screen.doorgelezen.data.models.AuthModel
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationAPI {
    @POST("/auth/login")
    suspend fun logIn(@Body data: AuthModel): Result<Response<ResponseBody>>

    @POST("/auth/logout")
    suspend fun logOut(): Result<Response<ResponseBody>>
}