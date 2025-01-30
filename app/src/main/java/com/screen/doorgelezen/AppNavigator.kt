package com.screen.doorgelezen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.screen.doorgelezen.screens.Authentication.AuthenticationScreen
import com.screen.doorgelezen.screens.Splash
import com.screen.doorgelezen.AppScreens.*
import com.screen.doorgelezen.screens.scanner.ScannerScreen
import com.screen.doorgelezen.screens.scanner.SearchBar
import com.screen.doorgelezen.screens.unassignedstock.UnassignedStockScreen
import com.screen.doorgelezen.viewModels.CatalogViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    val mainViewModel: MainViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = SPLASH.route,
        modifier = Modifier.padding()
    ) {

        composable(SPLASH.route) {
            Splash {
                navController.navigate(
                    if (mainViewModel.isAlreadyLoggedIn) {
                        SCANNER.route
                    } else {
                        AUTHENTICATION.route
                    }
                ){
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
        }

        composable(AUTHENTICATION.route) {
            AuthenticationScreen { route ->
                navController.navigate(route.route){
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
        }

        composable(UNASSIGNEDSTOCK.route) { UnassignedStockScreen() }

        composable(SCANNER.route) {
            ScannerScreen {
                navController.navigate(AUTHENTICATION.route){
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
        }
    }
}