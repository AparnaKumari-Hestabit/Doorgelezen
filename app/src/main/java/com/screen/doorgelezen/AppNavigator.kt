package com.screen.doorgelezen

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.screen.doorgelezen.screens.Authentication.AuthenticationScreen
import com.screen.doorgelezen.screens.Splash
import com.screen.doorgelezen.AppScreens.*
import com.screen.doorgelezen.screens.scanner.ScannerScreen
import com.screen.doorgelezen.screens.scanner.SearchBar
import com.screen.doorgelezen.screens.scanner.ScanContent
import com.screen.doorgelezen.screens.scanner.ScannerHome
import com.screen.doorgelezen.screens.scanner.ScannerScreen
import com.screen.doorgelezen.screens.unassignedstock.UnassignedStockScreen
import com.screen.doorgelezen.utils.printDebug
import com.screen.doorgelezen.viewModels.CatalogViewModel
import kotlinx.coroutines.launch
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    val mainViewModel: MainViewModel = hiltViewModel()

    val catalogViewModel: CatalogViewModel = hiltViewModel()

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

        composable(UNASSIGNED_STOCK.route) { UnassignedStockScreen() }

        composable(SCANNER.route) {

            ScannerHome(viewModel = catalogViewModel){
                navController.navigate(AUTHENTICATION.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }

        }

    }
}

@Composable
fun ScannerNavigator(modifier: Modifier, catalogViewModel: CatalogViewModel){
    val scannerNavController = rememberNavController()

    val isSearching by catalogViewModel.isSearching.collectAsState()

    if (isSearching){
        if(scannerNavController.currentBackStackEntry?.destination?.route ?: SCANNER_SCREEN.route == SCAN_CONTENT.route){
            scannerNavController.popBackStack()
        }
    }

    val focusManager = LocalFocusManager.current
    val context = LocalContext.current as Activity

    BackHandler {
        if(scannerNavController.currentBackStackEntry?.destination?.route ?: SCANNER_SCREEN.route == SCAN_CONTENT.route){
            scannerNavController.popBackStack()
        }else if(catalogViewModel.catalogResults.value.isNotEmpty()){
            catalogViewModel.setQuery()
            catalogViewModel.setSearching(false)
            catalogViewModel.clearCatalog()
        }else{
            context.finish()
        }
    }

    NavHost(
        navController = scannerNavController,
        startDestination = SCANNER_SCREEN.route,
        modifier = modifier){

        composable(route = SCANNER_SCREEN.route) {
            ScannerScreen(viewModel = catalogViewModel,
                onNavigateToContent = {
                    focusManager.clearFocus()
                    catalogViewModel.setQuery()
                    catalogViewModel.setSearching(false)
                    scannerNavController.navigate(SCAN_CONTENT.route)
                }
            )
        }

        composable(
            route = SCAN_CONTENT.route,
        ) {
            ScanContent(catalogViewModel)
        }
    }
}