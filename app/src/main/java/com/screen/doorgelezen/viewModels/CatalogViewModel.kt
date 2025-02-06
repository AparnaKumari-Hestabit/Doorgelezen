package com.screen.doorgelezen.viewModels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.screen.doorgelezen.data.models.BolProduct
import com.screen.doorgelezen.data.repository.CatalogRepository
import com.screen.doorgelezen.data.repository.Resource
import com.screen.doorgelezen.utils.printError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val catalogRepository: CatalogRepository
) : ViewModel() {

    private val _selectedCatalog = MutableStateFlow<BolProduct?>(null)
    val selectedCatalog = _selectedCatalog.asStateFlow()

    private val _catalogResults = MutableStateFlow<List<BolProduct>>(emptyList())
    val catalogResults: StateFlow<List<BolProduct>> = _catalogResults

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _error = MutableStateFlow("")
    val error = _error.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _isScanned = MutableStateFlow<Boolean>(false)
    val isScanned = _isScanned.asStateFlow()

    fun setQuery(query: String = ""){
        _searchQuery.value = query
    }

    fun updateSelectedCatalog(newCatalog:BolProduct?){
        _selectedCatalog.value = newCatalog
    }

    fun clearErrorState(){
        _error.value = ""
    }

    fun clearCatalog(){
        _catalogResults.value = emptyList()
    }

    fun setSearching(searching:Boolean){
        _isSearching.value = searching
    }

    fun setScanned(newState: Boolean){
        _isScanned.value = newState
    }

    fun search(query: String, isScanned: Boolean = false) = viewModelScope.launch {
        try {
            _isScanned.value = isScanned
            _isLoading.value = true
            val response = catalogRepository.searchCatalog(query)
            response.let {
                when(it){
                    is Resource.Failure -> {
                        _isLoading.value = false
                        _error.value = it.exception.message.toString()
                    }
                    Resource.Loading -> {
                        _isLoading.value = true
                    }
                    is Resource.Success -> {
                        _isLoading.value = false
                        if(isScanned){
                            _selectedCatalog.value = it.result.results.firstOrNull()
                        }else {
                            _catalogResults.value = it.result.results
                        }
                    }
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
            _isLoading.value = false
            printError(e.message.toString())
            _error.value = e.message.toString()
        }
    }
}