package com.screen.doorgelezen.screens.unassignedstock

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.screen.doorgelezen.R
import java.math.BigDecimal
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnassignedStockScreen(

) {
//    val smallPadding = dimensionResource(R.dimen.padding_small)
//
//    val stock = listOf(
//        OfferLocation(
//            offerId = UUID.randomUUID(),
//            locationId = Location.DEFAULT_ID,
//            volume = 6,
//            offer = Offer(
//                UUID.randomUUID(),
//                fulfilmentMethod = FulfilmentMethod.RETAILER,
//                deliveryMethod = DeliveryMethod.BOL,
//                condition = OfferCondition.NEW,
//                BigDecimal(9.99),
//                ean = "9781529034523",
//                title = "The Hitchhiker's Guide to the Galaxy"
//            )
//        ),
//        OfferLocation(
//            offerId = UUID.randomUUID(),
//            locationId = Location.DEFAULT_ID,
//            volume = 3,
//            offer = Offer(
//                UUID.randomUUID(),
//                fulfilmentMethod = FulfilmentMethod.BOL,
//                deliveryMethod = DeliveryMethod.BOL,
//                condition = OfferCondition.AS_NEW,
//                BigDecimal(16.56),
//                ean = "9781617293023",
//                title = "Type-driven Development with Idris"
//            )
//        )
//    )
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Text(
//                        text = stringResource(R.string.unassigned_stock),
//                        color = Color.White
//                    )
//                },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.primary
//                ),
//                actions = {
//                    IconButton(onClick = {  }) {
//                        Icon(
//                            imageVector = Icons.Default.ArrowForward,
//                            contentDescription = null,
//                            tint = Color.White
//                        )
//                    }
//                }
//            )
//        },
//        floatingActionButton = {
//            FloatingActionButton(
//                onClick = {},
//                containerColor = MaterialTheme.colorScheme.primary,
//                contentColor = MaterialTheme.colorScheme.onPrimary
//            ) {
//                Icon(
//                    painter = painterResource(id = R.drawable.qr_code),
//                    contentDescription = stringResource(R.string.scan),
//                    modifier = Modifier.size(24.dp)
//                )
//            }
//        }
//    ) { innerPadding ->
//
//        UnassignedStockList(
//            stock = stock,
//            onDecrement = { /* Handle decrement if needed */ },
//            modifier = Modifier
//                .padding(innerPadding)
//                .padding(vertical = smallPadding)
//                .fillMaxSize()
//        )
//    }
}

