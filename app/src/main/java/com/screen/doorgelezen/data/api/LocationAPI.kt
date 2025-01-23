package com.screen.doorgelezen.data.api

import com.screen.doorgelezen.data.models.Location
import retrofit2.http.GET
import retrofit2.http.Path

interface LocationAPI {
    @GET("locations")
    suspend fun listLocations(): Result<List<Location>>

    @GET("locations/{locationId}")
    suspend fun getLocation(@Path("locationId") locationId: String): Result<Location>
}