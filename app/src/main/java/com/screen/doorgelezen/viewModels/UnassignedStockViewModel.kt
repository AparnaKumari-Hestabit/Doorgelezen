package com.screen.doorgelezen.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.screen.doorgelezen.data.models.OfferLocation
import com.screen.doorgelezen.data.repository.Resource
import com.screen.doorgelezen.data.repository.StockRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UnassignedStockViewModel @Inject constructor(private val stockRepository: StockRepository) : ViewModel() {

    private val _stocksFlow = MutableStateFlow<Resource<List<OfferLocation>>?>(null)
    val stocksFlow = _stocksFlow.asStateFlow()

//    private val _isLoading = MutableStateFlow<Boolean>(false)
//    val isLoading = _isLoading.asStateFlow()

    init {
        getStocks("UNSET")
    }

    private fun getStocks(locationId:String) = viewModelScope.launch{
        _stocksFlow.value = Resource.Loading
        val result = stockRepository.getStocks(locationId = locationId, null)
        _stocksFlow.value = result
    }

}