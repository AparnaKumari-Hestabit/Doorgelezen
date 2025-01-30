package com.screen.doorgelezen.screens.scanner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DocumentScanner
import androidx.compose.material.icons.filled.Logout
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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.screen.doorgelezen.R
import com.screen.doorgelezen.ScannerNavigator
import com.screen.doorgelezen.data.repository.Resource
import com.screen.doorgelezen.utils.LogoutDialog
import com.screen.doorgelezen.viewModels.AuthViewModel
import com.screen.doorgelezen.viewModels.CatalogViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScannerHome(
    viewModel: CatalogViewModel,
    authViewModel: AuthViewModel = hiltViewModel(),
    onLogout: () -> Unit
) {

    ScanDataReceiver(stringResource(R.string.scan_intent_action), viewModel::search)

    var showLogoutAlert by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val logoutFlow by authViewModel.loginFlow.collectAsState()
    logoutFlow.let {
        when (it) {
            is Resource.Success -> {
                onLogout()
            }

            else -> {
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
                    SearchBar(search = viewModel::search, viewModel)
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

        ScannerNavigator(modifier = Modifier.padding(paddingValues), catalogViewModel = viewModel)

        if (showLogoutAlert) {
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