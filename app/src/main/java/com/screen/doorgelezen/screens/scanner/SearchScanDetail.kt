package com.screen.doorgelezen.screens.scanner

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DocumentScanner
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.screen.doorgelezen.data.models.ScanWithThirdPartyOffers
import com.screen.doorgelezen.R

@Composable
fun SearchScanDetail(
    scan: ScanWithThirdPartyOffers?
) {
    if (scan == null) {
        EmptyState(message = stringResource(R.string.scan_or_search_manually)) { size ->
            Icon(
                Icons.Default.DocumentScanner,
                contentDescription = stringResource(R.string.scanner),
                modifier = Modifier.size(size),
                tint = MaterialTheme.colorScheme.secondary
            )
        }
    } else {
        SearchListing()
    }
}