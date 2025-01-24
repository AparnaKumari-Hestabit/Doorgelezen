package com.screen.doorgelezen.screens.unassignedstock

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.screen.doorgelezen.R
import java.math.BigDecimal
import java.util.UUID

@SuppressLint("SuspiciousIndentation")
@Composable
fun UnassignedStockList(
//    stock: List<OfferLocation>,
//    onDecrement: (OfferLocation) -> Unit,
    modifier: Modifier = Modifier
) {
    val paddingMedium = dimensionResource(R.dimen.padding_medium)

//        LazyColumn(modifier = modifier) {
//            items(items = stock) { offerLocation ->
//                UnassignedStockRow(
//                    offerLocation = offerLocation,
//                    onSelectedChange = { onDecrement(offerLocation) },
//                    modifier = Modifier.padding(paddingMedium)
//                )
//            }
//        }

}

@Composable
@Preview
fun UnassignedStockListPreview() {
    val offerId = UUID.randomUUID()
    val altOfferId = UUID.randomUUID()
//    UnassignedStockList(
//        stock = listOf(
//            OfferLocation(
//                offerId,
//                Location.DEFAULT_ID,
//                6,
//                Offer(
//                    offerId,
//                    FulfilmentMethod.RETAILER,
//                    DeliveryMethod.BOL,
//                    OfferCondition.NEW,
//                    BigDecimal(9.99),
//                    "9781529034523",
//                    "The Hitchhiker's Guide to the Galaxy"
//                )
//            ),
//            OfferLocation(
//                altOfferId,
//                Location.DEFAULT_ID,
//                3,
//                Offer(
//                    altOfferId,
//                    FulfilmentMethod.BOL,
//                    DeliveryMethod.BOL,
//                    OfferCondition.AS_NEW,
//                    BigDecimal(16.56),
//                    "9781617293023",
//                    "Type-driven Development with Idris"
//                )
//            )
//        ),
//        {}
//    )
}