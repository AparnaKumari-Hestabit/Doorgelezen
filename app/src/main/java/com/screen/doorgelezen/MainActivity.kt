package com.screen.doorgelezen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.DisposableEffect
import androidx.core.view.WindowCompat
import com.screen.doorgelezen.ui.theme.DoorgelezenTheme
import com.screen.doorgelezen.utils.TimeBasedCrashCheck
import com.screen.doorgelezen.utils.printDebug
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private fun setStatusBarColor(darkIcons: Boolean) {
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.isAppearanceLightStatusBars = darkIcons
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            setStatusBarColor(false)

            Surface(
               color = MaterialTheme.colorScheme.surface
            ) {


                DoorgelezenTheme {
                    AppNavigator()
                }
            }
        }
    }
}