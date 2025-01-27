package com.screen.doorgelezen.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.screen.doorgelezen.data.models.ApiResponse
import com.screen.doorgelezen.data.models.BolProduct
import com.screen.doorgelezen.data.models.CatalogResults
import com.screen.doorgelezen.data.models.NoProductsFoundError
import com.screen.doorgelezen.data.models.Scan
import com.screen.doorgelezen.data.models.ScanWithThirdPartyOffers
import com.screen.doorgelezen.data.repository.CatalogRepository
import com.screen.doorgelezen.utils.ErrorManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val catalogRepository: CatalogRepository,
) : ViewModel() {

    private var searchJob: Job? = null
    var scan: ScanWithThirdPartyOffers? by mutableStateOf(null)
    var queryError by mutableStateOf<NoProductsFoundError?>(null)

    fun search(query: String) {
        queryError = null
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(DEBOUNCE_TIME_IN_MS)
            val result = catalogRepository.searchCatalog("1")
            Log.e("CatalogTAG : ", "jsonResponse  $result")
//            handleSearchResult(result, query)
        }
    }

//    private suspend fun handleSearchResult(
//        searchResult: ApiResponse,
//        query: String
//    ) {
//        when (searchResult) {
//            is ApiResponse.Success ->{
//                val result: Result<BolProduct?> = Result.success(searchResult.product)
//
//
//                val scanWithOffers = createScanWithOffers(product)
//                scan = scanWithOffers
//            }
//            is ApiResponse.Error -> {
//                if (searchResult.message.contains("Network")){
//
//                }
//                else {
//                    queryError = NoProductsFoundError(query)
//                    scan = null
//                }
//            }
//        }
//    }
//
//
//    private fun createScanWithOffers(
//        product: BolProduct,
//    ): ScanWithThirdPartyOffers = ScanWithThirdPartyOffers(
//        Scan(
//            product.title,
//            product.ean,
//            imageUrl = product.imageURL,
//            soldByBol = product.soldByBol
//        ),
//        product.offers,
//        product.dbResults,
//        product.assets
//    )
//
    companion object {
        const val DEBOUNCE_TIME_IN_MS: Long = 300
    }
}