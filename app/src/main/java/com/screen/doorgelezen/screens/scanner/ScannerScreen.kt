package com.screen.doorgelezen.screens.scanner

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.screen.doorgelezen.R
import com.screen.doorgelezen.viewModels.CatalogViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScannerScreen(viewModel: CatalogViewModel = hiltViewModel(), onNavigateToContent: (String) -> Unit) {

    val catalogResults by viewModel.catalogResults.collectAsState()

    ScanDataReceiver(stringResource(R.string.scan_intent_action), viewModel::search)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.scanner)) },
                actions = {
                    SearchBar(search = viewModel::search)
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {

                Spacer(modifier = Modifier.height(16.dp))

                //SearchBar query Content
                if (catalogResults.isEmpty()) {
                    EmptyState(message = stringResource(R.string.scan_or_search_manually)) { size ->
                        Icon(
                            painter = painterResource(id = R.drawable.document_scan),
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
    )
}