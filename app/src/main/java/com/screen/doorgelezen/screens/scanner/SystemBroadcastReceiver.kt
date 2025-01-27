package com.screen.doorgelezen.screens.scanner

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat


@Composable
fun SystemBroadcastReceiver(
    systemAction: String,
    onSystemEvent: (intent: Intent?) -> Unit
){
    val context = LocalContext.current
    DisposableEffect(systemAction, context) {
        val broadcast = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                onSystemEvent(intent)
            }
        }
        ContextCompat.registerReceiver(
            context,
            broadcast,
            IntentFilter(systemAction),
            ContextCompat.RECEIVER_EXPORTED
        )

        onDispose {
            context.unregisterReceiver(broadcast)
        }
    }
}