package com.screen.doorgelezen.data.repository

import com.screen.doorgelezen.data.models.OfferLocation

interface StockRepository {

    suspend fun getStocks(locationId:String, ean:String?): Resource<List<OfferLocation>>

}