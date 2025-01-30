package com.screen.doorgelezen.screens.scanner

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.screen.doorgelezen.R
import com.screen.doorgelezen.data.repository.Resource
import com.screen.doorgelezen.viewModels.AuthViewModel
import com.screen.doorgelezen.viewModels.CatalogViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScannerScreen(
    viewModel: CatalogViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel(),
    onLogout: () -> Unit
) {

    ScanDataReceiver(stringResource(R.string.scan_intent_action), viewModel::search)
    var showLogoutAlert by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val isLoading by authViewModel.isLoading.collectAsState()
    val logoutFlow by authViewModel.loginFlow.collectAsState()
    logoutFlow.let {
        when(it){
            is Resource.Success -> {
                onLogout()
            }
            else-> {
                //TODO: Already handled
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.scanner), color = Color.White) },
                actions = {
                    SearchBar(search = viewModel::search)
                    IconButton({
                        showLogoutAlert = true
                    }) {
                        Icon(Icons.Default.Logout, "Logout", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        })
    { paddingValues ->

        if(isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(2f)
                    .background(Color.Black.copy(0.5f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            SearchScanDetail(viewModel.scan)
        }

        if(showLogoutAlert) {
            LogoutDialog(onCancel = {
                showLogoutAlert = false
            }) {
                showLogoutAlert = false
                authViewModel.logout { message ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = message ?: "Something went wrong",
                            duration = SnackbarDuration.Short
                        )
                    }
                }

            }
        }
    }


}

@Composable
fun LogoutDialog(
    onCancel: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(Icons.Default.Logout, "Logout")
                Text("Logout")
            }
        },
        text = { Text("Are you sure you want to logout of your account ?") },
        onDismissRequest = {
            onCancel()
        },
        confirmButton = {
            TextButton({onConfirm()}) {
                Text("Logout")
            }
        },
        dismissButton = {
            TextButton({onCancel()}) {
                Text("Cancel")
            }
        },
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SearchScreen() {
    ScannerScreen(){}
}