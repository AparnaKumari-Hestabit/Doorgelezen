package com.screen.doorgelezen.data.api

import com.screen.doorgelezen.data.models.OfferLocation
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StockAPI {

    @GET("locations/{locationId}/stock")
    suspend fun listStock(
        @Path("locationId") locationId: String,
        @Query("ean") ean: String?
    ): Result<List<OfferLocation>>

}