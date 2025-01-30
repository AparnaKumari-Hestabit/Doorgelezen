package com.screen.doorgelezen.data.repository

import com.screen.doorgelezen.data.models.BolProduct
import com.screen.doorgelezen.data.models.CatalogResults

interface CatalogRepository {

    companion object {
        const val BOOK_CATEGORY_ID = 8299
        var selectedProduct: BolProduct? = null
    }

    suspend fun searchCatalog(query:String) :Resource<CatalogResults>

}