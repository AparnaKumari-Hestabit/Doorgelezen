package com.screen.doorgelezen.data.repository

import com.screen.doorgelezen.data.api.CatalogAPI
import com.screen.doorgelezen.data.models.ApiResponse
import com.screen.doorgelezen.data.models.CatalogResults
import retrofit2.Response
import javax.inject.Inject


class CatalogRepository @Inject constructor(private val service: CatalogAPI) {
    suspend fun searchCatalog(query: String) = service.search(query, BOOK_CATEGORY_ID)

    companion object {
        const val BOOK_CATEGORY_ID = 8299
    }
}