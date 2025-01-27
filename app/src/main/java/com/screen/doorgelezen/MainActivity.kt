package com.screen.doorgelezen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.screen.doorgelezen.screens.scanner.ScannerScreen
import com.screen.doorgelezen.screens.unassignedstock.UnassignedStockScreen
import com.screen.doorgelezen.ui.theme.DoorgelezenTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DoorgelezenTheme {
//                AppNavigator()
                ScannerScreen()
            }
        }
    }
}