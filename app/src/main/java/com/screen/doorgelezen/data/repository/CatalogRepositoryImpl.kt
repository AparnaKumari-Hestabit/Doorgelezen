package com.screen.doorgelezen.data.repository

import com.screen.doorgelezen.data.api.CatalogAPI
import com.screen.doorgelezen.data.models.CatalogResults
import com.screen.doorgelezen.data.repository.CatalogRepository.Companion.BOOK_CATEGORY_ID
import com.screen.doorgelezen.utils.printDebug
import javax.inject.Inject

class CatalogRepositoryImpl @Inject constructor(private val catalogAPI: CatalogAPI) : CatalogRepository{
    override suspend fun searchCatalog(query: String): Resource<CatalogResults> {
        return try {
            val response = catalogAPI.search(query, BOOK_CATEGORY_ID)
            if (!response.isSuccessful) return Resource.Failure(Exception("Something went wrong!"))
            if (response.body() == null) return Resource.Failure(Exception("No data found!"))

            printDebug(response.body()!!.toString())
            Resource.Success(response.body()!!)
        }catch (e:Exception){
            e.printStackTrace()
            printDebug("catalog error - ${e.message}")
            Resource.Failure(e)
        }
    }
}
