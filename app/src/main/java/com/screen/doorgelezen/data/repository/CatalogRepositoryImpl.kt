package com.screen.doorgelezen.data.repository

import com.screen.doorgelezen.data.api.CatalogAPI
import com.screen.doorgelezen.data.models.BolProduct
import com.screen.doorgelezen.data.models.Calculated
import com.screen.doorgelezen.data.models.CatalogResults
import com.screen.doorgelezen.data.models.ConditionPrice
import com.screen.doorgelezen.data.models.DbResult
import com.screen.doorgelezen.utils.printDebug
import javax.inject.Inject

class CatalogRepositoryImpl @Inject constructor(private val catalogAPI: CatalogAPI) : CatalogRepository{
    override suspend fun searchCatalog(query: String): Resource<CatalogResults> {
        return try {
//            val response = catalogAPI.search(query, BOOK_CATEGORY_ID)
//            if (!response.isSuccessful) return Resource.Failure(Exception("Something went wrong!"))
//            if (response.body() == null) return Resource.Failure(Exception("No data found!"))

//            printDebug(response.body()!!.toString())
//            Resource.Success(response.body()!!)
            val mockResults = arrayListOf(
                BolProduct(
                    ean = "1234567890",
                    title = "Sample Product Title",
                    imageURL = null,
                    offers = emptyList(),
                    dbResults = listOf(
                        DbResult(
                            id = 1,
                            isbn = "1234567890",
                            rank = 1,
                            count = 10,
                            category = "Category 1"
                        ),
                        DbResult(
                            id = 1,
                            isbn = "1234567890",
                            rank = 7890,
                            count = 10,
                            category = "Category 2"
                        )
                    ),
                    soldByBol = true,
                    assets = emptyList(),
                    calculated = null
                ),
                BolProduct(
                    ean = "0987654321",
                    title = "Another Sample Product Another Sample Product Another Sample Product Another Sample Product Another Sample Product",
                    imageURL = null,
                    offers = emptyList(),
                    dbResults = emptyList(),
                    soldByBol = true,
                    assets = emptyList(),
                    calculated = Calculated(
                        newCondition = ConditionPrice(price = 19.99, maxBid = 15.50),
                        asNewCondition = ConditionPrice(price = 17.99, maxBid = 14.00),
                        goodCondition = ConditionPrice(price = 14.99, maxBid = 10.00)
                    )
                )
            )

            val catalogResults = CatalogResults(results = mockResults)

            Resource.Success(catalogResults)
        }catch (e:Exception){
            e.printStackTrace()
            printDebug("catalog error - ${e.message}")
            Resource.Failure(e)
        }
    }
}
