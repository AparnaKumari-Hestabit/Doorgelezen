package com.screen.doorgelezen.screens.scanner

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.screen.doorgelezen.R


@Composable
fun ScanDataReceiver(
    scanAction: String,
    onScanData: (String) -> Unit
){
    val dataKey = stringResource(R.string.datawedge_intent_key_data)
    SystemBroadcastReceiver(scanAction) { intent ->
        val data = intent?.getStringExtra(dataKey) ?: return@SystemBroadcastReceiver
        onScanData(data)
    }
}