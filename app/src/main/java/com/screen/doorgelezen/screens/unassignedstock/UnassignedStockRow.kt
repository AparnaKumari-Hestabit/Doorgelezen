package com.screen.doorgelezen.screens.unassignedstock


import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.screen.doorgelezen.data.models.OfferLocation

@Composable
fun UnassignedStockRow(
    offerLocation: OfferLocation,
    onSelectedChange: (selected: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    OfferLocationRow(
        offerLocation = offerLocation,
        modifier = modifier,
        additionalMetadata = null
    ) {
        IncrementButton(
            onChange = onSelectedChange,
            selected = offerLocation.volume,
            maxSelected = offerLocation.volume
        )
    }
}
