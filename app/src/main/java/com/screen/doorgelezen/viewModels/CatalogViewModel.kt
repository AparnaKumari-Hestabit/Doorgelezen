package com.screen.doorgelezen.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.screen.doorgelezen.data.models.BolProduct
import com.screen.doorgelezen.data.repository.CatalogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val repository: CatalogRepository
) : ViewModel() {

    private val _catalogResults = MutableStateFlow<List<BolProduct>>(emptyList())
    val catalogResults: StateFlow<List<BolProduct>> = _catalogResults

    fun search(query: String) {
        viewModelScope.launch {
            try {
                val response = repository.searchCatalog(query)
                if (response.isSuccessful) {
                    val results = response.body()?.results?.map { product ->
                        product.copy(uuid = UUID.randomUUID().toString())
                    } ?: emptyList()
                    _catalogResults.value = results
                } else {
                    Log.e("CatalogViewModel", "API Error: ${response.errorBody()}")
                }
            } catch (e: Exception) {
                Log.e("CatalogViewModel", "Exception: ${e.message}", e)
            }
        }
    }
}