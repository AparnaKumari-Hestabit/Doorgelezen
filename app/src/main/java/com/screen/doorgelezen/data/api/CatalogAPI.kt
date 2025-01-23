package com.screen.doorgelezen.data.api

import com.google.gson.JsonElement
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CatalogAPI {
    @GET("catalog/search")
    suspend fun search(
        @Query("query") query: String,
        @Query("category_id") categoryId: Int
    ): Response<JsonElement>
}
