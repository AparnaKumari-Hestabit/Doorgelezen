package com.screen.doorgelezen.screens.scanner

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DocumentScanner
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.screen.doorgelezen.R
import com.screen.doorgelezen.data.models.BolProduct
import com.screen.doorgelezen.viewModels.AuthViewModel
import com.screen.doorgelezen.viewModels.CatalogViewModel


@Composable
fun ScannerScreen(
    viewModel: CatalogViewModel,
    authViewModel: AuthViewModel = hiltViewModel(),
    onNavigateToContent: (BolProduct) -> Unit,
    snackbarHostState: SnackbarHostState
) {

    val catalogResults by viewModel.catalogResults.collectAsState()
    val isCatalogLoading by viewModel.isLoading.collectAsState()
    val isCatalogError by viewModel.error.collectAsState()
    val isScanned by viewModel.isScanned.collectAsState()
    val selectedCatalog by viewModel.selectedCatalog.collectAsState()

    LaunchedEffect(isCatalogError){
        if (isCatalogError.isNotEmpty()) {
            snackbarHostState.showSnackbar(
                message = isCatalogError,
                duration = SnackbarDuration.Short
            )
            viewModel.clearErrorState()
        }
    }

//    ScanDataReceiver(stringResource(R.string.scan_intent_action)){ query ->
//        viewModel.search(query, true)
//    }

    val isLoading by authViewModel.isLoading.collectAsState()

    if (isLoading || isCatalogLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(2f)
                .background(Color.Black.copy(0.5f))
                .clickable(enabled = false){},
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color.White)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding()
    ) {
//      SearchBar query Content
        if(isScanned){
            if(selectedCatalog != null){
                onNavigateToContent(selectedCatalog!!)
                viewModel.setScanned(false)
            }
        }else {
            if (catalogResults.isEmpty()) {
                EmptyState(message = stringResource(R.string.scan_or_search_manually)) { size ->
                    Icon(
                        Icons.Default.DocumentScanner,
                        contentDescription = stringResource(R.string.scanner),
                        modifier = Modifier.size(size),
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
            } else {
                SearchListing(results = catalogResults, onNavigate = onNavigateToContent)
            }
        }
    }
}
