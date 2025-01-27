package com.screen.doorgelezen.data.repository

import com.screen.doorgelezen.data.api.StockAPI
import com.screen.doorgelezen.data.models.OfferLocation
import javax.inject.Inject

interface StockRepository {

    suspend fun getStocks(locationId:String, ean:String?): Resource<List<OfferLocation>>

}