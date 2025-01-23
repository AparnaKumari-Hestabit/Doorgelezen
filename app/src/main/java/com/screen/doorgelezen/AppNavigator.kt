package com.screen.doorgelezen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.screen.doorgelezen.screens.AuthenticationScreen
import com.screen.doorgelezen.screens.Splash
import com.screen.doorgelezen.AppScreens.*

@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    Scaffold { innerPadding ->

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
                        navController.navigate(AUTHENTICATION.route)
                    }
                }
                composable(AUTHENTICATION.route) { AuthenticationScreen() }

            }
        }
    }
}