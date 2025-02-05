package com.screen.doorgelezen.data.repository

import android.content.Context
import com.screen.doorgelezen.data.api.CatalogAPI
import com.screen.doorgelezen.data.models.CatalogResults
import com.screen.doorgelezen.data.repository.CatalogRepository.Companion.BOOK_CATEGORY_ID
import com.screen.doorgelezen.utils.printDebug
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import com.screen.doorgelezen.R

class CatalogRepositoryImpl @Inject constructor(private val catalogAPI: CatalogAPI, @ApplicationContext private val context: Context) : CatalogRepository{
    override suspend fun searchCatalog(query: String): Resource<CatalogResults> {
         try {
            val response = catalogAPI.search(query, BOOK_CATEGORY_ID)
            val mockResults = response.body()!!.results
            if (!response.isSuccessful) return Resource.Failure(Exception(context.getString(R.string.generic_error)))
            if (response.body() == null) return Resource.Failure(Exception(context.getString(R.string.no_product_found)))
            if (response.isSuccessful && response.body()!!.results.isEmpty()) return Resource.Failure(Exception(context.getString(R.string.no_product_found)))

            printDebug("response body - ${response.body()!!.toString()}")
//            Resource.Success(response.body()!!)

            val catalogResults = CatalogResults(results = mockResults)

            return Resource.Success(catalogResults)
        }catch (e:Exception){
            e.printStackTrace()
            printDebug("catalog error - ${e.message}")
            return Resource.Failure(Exception(context.getString(R.string.generic_error)))
        }
    }
}
