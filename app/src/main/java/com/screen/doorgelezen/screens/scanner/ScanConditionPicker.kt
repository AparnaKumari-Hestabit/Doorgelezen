package com.screen.doorgelezen.screens.scanner

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PriceChange
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.screen.doorgelezen.R
import com.screen.doorgelezen.data.models.OfferCondition
import com.screen.doorgelezen.data.models.ThirdPartyOffer
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

@Composable
fun ScanConditionPicker(
    offers: List<ThirdPartyOffer>,
    soldByBol: Boolean
) {
    val smallPadding = dimensionResource(R.dimen.padding_small)
    val extra_smallPadding = dimensionResource(R.dimen.padding_extra_small)
    val largePadding = dimensionResource(R.dimen.padding_large)

    fun formatPrice(price: BigDecimal, currencyCode: String): String {
        val currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
        currencyFormat.currency = Currency.getInstance(currencyCode)
        return currencyFormat.format(price)
    }

    val sortedOffers = offers.sortedBy {
        when (it.condition) {
            OfferCondition.NEW -> 1
            OfferCondition.AS_NEW -> 2
            OfferCondition.GOOD -> 3
            else -> 4
        }
    }

    Column(
        Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(
            stringResource(R.string.cheapest_bol_offers),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = smallPadding)
        )

        if(offers.isEmpty()) {
            //no offer found content

//            Column {
//                Text(
//                    stringResource(R.string.no_bol_offers),
//                    textAlign = TextAlign.Center,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(top = largePadding)
//                )
//                Text(
//                    stringResource(R.string.no_bol_offers_msg),
//                    textAlign = TextAlign.Center,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(top = extra_smallPadding, bottom = largePadding)
//                )
//            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = largePadding, bottom = largePadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.no_bol_offers) + "\n" + stringResource(R.string.no_bol_offers_msg),
                    textAlign = TextAlign.Center
                )
            }
        }
        else {
            //offer found
            sortedOffers.forEachIndexed { index, offer ->
                val isNewCondition = offer.condition == OfferCondition.NEW
                val isNew =
                    isNewCondition && sortedOffers.lastOrNull { it.condition == OfferCondition.NEW } == offer

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = extra_smallPadding),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = stringResource(id = R.string.max_bid),
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                Icons.Outlined.PriceChange,
                                stringResource(R.string.condition),
                                tint = MaterialTheme.colorScheme.onSurface
                            )
//                        Text(
//                            text = when (offer.condition) {
//                                OfferCondition.NEW -> "Nieuw"
//                                OfferCondition.AS_NEW -> "Als nieuw"
//                                OfferCondition.GOOD -> "Goed"
//                                else -> offer.condition.name
//                            },
//                            style = MaterialTheme.typography.bodyLarge,
//                            fontWeight = if (isNewCondition) FontWeight.Bold else FontWeight.Normal
//                        )
                            Text(
                                text = stringResource(id = offer.condition.stringResourceId),
                                style = MaterialTheme.typography.bodyLarge
                            )
                            if (isNewCondition && soldByBol) {
                                Icon(
                                    Icons.Outlined.Verified,
                                    stringResource(R.string.sold_by_bol_checkmark),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }

                        Text(
                            text = formatPrice(offer.price, "EUR"),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }

                    if (isNew) {
                        HorizontalDivider(
                            color = MaterialTheme.colorScheme.onBackground,
                            thickness = extra_smallPadding,
                            modifier = Modifier.padding(vertical = extra_smallPadding)
                        )
                    }
                }
            }
        }
    }
}