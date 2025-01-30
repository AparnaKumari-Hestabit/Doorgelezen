package com.screen.doorgelezen.screens.scanner

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.screen.doorgelezen.R

@Composable
fun EmptyState(
    message: String,
    icon: @Composable (Dp) -> Unit
) {
    val paddingExtraLarge = dimensionResource(R.dimen.padding_large)
    val iconSize = dimensionResource(R.dimen.empty_state_icon_size)
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        icon(iconSize)
        Text(
            message,
            modifier = Modifier.padding(top = paddingExtraLarge),
            style = MaterialTheme.typography.titleLarge
        )
    }
}