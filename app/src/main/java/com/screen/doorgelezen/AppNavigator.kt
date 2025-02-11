package com.screen.doorgelezen

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
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
import com.screen.doorgelezen.screens.Authentication.AuthenticationScreen
import com.screen.doorgelezen.screens.Splash
import com.screen.doorgelezen.AppScreens.*
import com.screen.doorgelezen.screens.scanner.ScannerScreen
import com.screen.doorgelezen.screens.scanner.ScanContent
import com.screen.doorgelezen.screens.scanner.ScannerHome
import com.screen.doorgelezen.screens.unassignedstock.UnassignedStockScreen
import com.screen.doorgelezen.viewModels.CatalogViewModel
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.screen.doorgelezen.screens.scanner.ScanDataReceiver
import com.screen.doorgelezen.utils.printDebug
import com.screen.doorgelezen.utils.raiseToast
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


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
                    catalogViewModel.clearCatalog()
                }
            }

        }

    }
}

@Composable
fun ScannerNavigator(modifier: Modifier, catalogViewModel: CatalogViewModel, snackbarHostState: SnackbarHostState){
    val scannerNavController = rememberNavController()

    val isSearching by catalogViewModel.isSearching.collectAsState()
    val coroutineScope = rememberCoroutineScope()


    if (isSearching){
        if(scannerNavController.currentBackStackEntry?.destination?.route ?: SCANNER_SCREEN.route == SCAN_CONTENT.route){
            catalogViewModel.setScanned(false)
            scannerNavController.popBackStack()
            coroutineScope.launch {
                delay(1000)
                catalogViewModel.updateSelectedCatalog(null)
            }
        }
        catalogViewModel.clearCatalog()
    }

    val focusManager = LocalFocusManager.current
    val context = LocalContext.current as Activity
    var backCounter by remember {
        mutableStateOf(false)
    }

    ScanDataReceiver(stringResource(R.string.scan_intent_action)){ query ->
        catalogViewModel.search(query, true)
    }

    var canNavigate = true

    BackHandler {
        if(scannerNavController.currentBackStackEntry?.destination?.route ?: SCANNER_SCREEN.route == SCAN_CONTENT.route){
            catalogViewModel.setScanned(false)
            scannerNavController.popBackStack()
            canNavigate = false
            coroutineScope.launch {
                delay(500)
                catalogViewModel.updateSelectedCatalog(null)
                canNavigate = true
            }
        }else if(catalogViewModel.catalogResults.value.isNotEmpty()){
            catalogViewModel.setQuery()
            catalogViewModel.setSearching(false)
            catalogViewModel.clearCatalog()
        }else{
            if (!backCounter) {
                backCounter = true
                raiseToast(context, context.getString(R.string.BACK_PRESS_MSG), Toast.LENGTH_SHORT)
            } else {
                context.finish()
            }
            coroutineScope.launch {
                delay(2000)
                backCounter = false
            }
        }
    }

    NavHost(
        navController = scannerNavController,
        startDestination = SCANNER_SCREEN.route,
        modifier = modifier){

        composable(route = SCANNER_SCREEN.route) {
            ScannerScreen(viewModel = catalogViewModel,
                onNavigateToContent = { selectedProduct ->
                    if(canNavigate) {
                        canNavigate = false
                        focusManager.clearFocus()
                        catalogViewModel.setQuery()
                        catalogViewModel.setSearching(false)
                        catalogViewModel.updateSelectedCatalog(selectedProduct)
                        scannerNavController.navigate(SCAN_CONTENT.route)
                        coroutineScope.launch {
                            delay(500)
                            canNavigate = true
                        }
                    }
                },
                snackbarHostState = snackbarHostState
            )
        }

        composable(
            route = SCAN_CONTENT.route,
        ) {
            ScanContent(catalogViewModel)
        }
    }
}