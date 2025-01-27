package com.screen.doorgelezen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.screen.doorgelezen.screens.Authentication.AuthenticationScreen
import com.screen.doorgelezen.screens.Splash
import com.screen.doorgelezen.AppScreens.*
import com.screen.doorgelezen.screens.unassignedstock.UnassignedStockScreen
import kotlinx.coroutines.launch

@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val mainViewModel: MainViewModel = hiltViewModel()


    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState){ snackbarData ->
            Snackbar(
                snackbarData = snackbarData,
                contentColor = Color.White,
                containerColor = MaterialTheme.colorScheme.error

            )
        } }
    ) { innerPadding ->

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            NavHost(
                navController = navController,
                startDestination = SPLASH.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(SPLASH.route) {
                    Splash{
                        navController.navigate(if(mainViewModel.isAlreadyLoggedIn){
                            HOME.route
                        }else {
                            AUTHENTICATION.route
                        })
                    }
                }
                composable(AUTHENTICATION.route) {
                    AuthenticationScreen(
                    showErrorSnackbar = {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Check your credentials or try again later!",
                                duration = SnackbarDuration.Short
                            )
                        }
                    }){ route ->
                        navController.navigate(route.route)
                    }
                }

                composable(HOME.route) { UnassignedStockScreen() }
            }
        }
    }
}