package com.screen.doorgelezen.data.repository

import com.screen.doorgelezen.data.api.StockAPI
import com.screen.doorgelezen.data.models.OfferLocation
import com.screen.doorgelezen.utils.printDebug
import javax.inject.Inject

class StockRepositoryImpl @Inject constructor(private val stockAPI: StockAPI) :StockRepository {

    override suspend fun getStocks(locationId: String, ean: String?): Resource<List<OfferLocation>> {
        return try {
            printDebug("stocks fetching")
            val result = stockAPI.listStock(locationId, ean)
            printDebug("stocks result - $result")
            if(result.isFailure) Resource.Failure(Exception("Something went wrong!"))

            val stocks = result.getOrNull()
            printDebug("stocks - $stocks")
            if(stocks == null) Resource.Failure(Exception("Something went wrong!"))

            Resource.Success(stocks!!)
        }catch (e:Exception){
            e.printStackTrace()
            printDebug("stocks failure - ${e.message}")
            Resource.Failure(e)
        }
    }

}