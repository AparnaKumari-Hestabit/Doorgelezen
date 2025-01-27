package com.screen.doorgelezen.screens.scanner

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.screen.doorgelezen.R
import com.screen.doorgelezen.viewModels.CatalogViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScannerScreen(viewModel: CatalogViewModel = hiltViewModel()){

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
//               TODO
                SearchScanDetail(viewModel.scan)
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SearchScreen(){
    ScannerScreen()
}