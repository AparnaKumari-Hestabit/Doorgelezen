package com.screen.doorgelezen.data.repository

import android.util.Log
import com.screen.doorgelezen.data.api.CatalogAPI
import com.screen.doorgelezen.data.models.BolProduct
import com.screen.doorgelezen.data.models.CatalogResults
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton


//class CatalogRepository @Inject constructor(private val service: CatalogAPI) {
//    suspend fun searchCatalog(query: String): Result<CatalogResults> {
//        return try {
//            val response = service.search(query, BOOK_CATEGORY_ID)
//            Log.e("CatalogRepoTAG ","API result  $response")
//            if (response.isSuccessful && response.body() != null) {
//                Result.success(response.body()!!)
//            } else {
//                Result.failure(Exception("API call failed with code: ${response.code()}"))
//            }
//        } catch (e: Exception) {
//            Log.e("CatalogRepo", "Exception occurred: ${e.message}")
//            Result.failure(e)
//        }
//    }
//
//    companion object {
//        const val BOOK_CATEGORY_ID = 8299
//    }
//}


class CatalogRepository @Inject constructor(private val service: CatalogAPI) {
    suspend fun searchCatalog(query: String) = service.search(query, BOOK_CATEGORY_ID)

    companion object {
        const val BOOK_CATEGORY_ID = 8299
    }
}
