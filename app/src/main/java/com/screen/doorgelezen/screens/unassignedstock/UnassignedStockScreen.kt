package com.screen.doorgelezen.screens.unassignedstock

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.screen.doorgelezen.R
import com.screen.doorgelezen.data.repository.Resource
import com.screen.doorgelezen.viewModels.UnassignedStockViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnassignedStockScreen() {

    BackHandler {
        //TODO: Handle back
    }

    val smallPadding = dimensionResource(R.dimen.padding_small)

    val viewModel: UnassignedStockViewModel = hiltViewModel()
    val stocks = viewModel.stocksFlow.collectAsState()

//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Text(
//                        text = stringResource(R.string.unassigned_stock),
//                        color = Color.White
//                    )
//                },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.primary
//                ),
//                actions = {
//                    IconButton(onClick = {  }) {
//                        Icon(
//                            imageVector = Icons.Default.ArrowForward,
//                            contentDescription = null,
//                            tint = Color.White
//                        )
//                    }
//                }
//            )
//        },
//        floatingActionButton = {
//            FloatingActionButton(
//                onClick = {},
//                containerColor = MaterialTheme.colorScheme.primary,
//                contentColor = MaterialTheme.colorScheme.onPrimary
//            ) {
//                Icon(
//                    painter = painterResource(id = R.drawable.qr_code),
//                    contentDescription = stringResource(R.string.scan),
//                    modifier = Modifier.size(24.dp)
//                )
//            }
//        }
//    ) { innerPadding ->

    stocks.value.let {
        when(it){
            is Resource.Failure -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    Text("Cannot able to fetch stock !")
                }
            }
            Resource.Loading -> {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(0.5f)), contentAlignment = Alignment.Center){
                    CircularProgressIndicator(color = Color.White)
                }
            }
            is Resource.Success -> {
                UnassignedStockList(
                    stock = it.result,
                    onDecrement = { /* Handle decrement if needed */ },
                    modifier = Modifier
                        .padding()
                        .padding(vertical = smallPadding)
                        .fillMaxSize()
                )
            }
            null -> {

            }
        }
    }

}

