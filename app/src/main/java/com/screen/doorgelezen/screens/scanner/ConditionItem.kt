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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.screen.doorgelezen.R
import com.screen.doorgelezen.data.models.ConditionPrice
import com.screen.doorgelezen.utils.formatPrice

@Composable
fun ConditionItem(
    condition: String,
    priceDetails: ConditionPrice,
    soldByBol: Boolean,
    isFirst: Boolean,
    isDividerVisible: Boolean
) {
    val extraSmallPadding = dimensionResource(R.dimen.padding_extra_small)

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = extraSmallPadding),
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
                Text(
                    text = condition,
                    style = MaterialTheme.typography.headlineSmall,
                    fontSize = 19.sp,
                    fontStyle = FontStyle.Italic
                )
                if (soldByBol && condition == stringResource(id = R.string.new_)) {
                    Icon(
                        Icons.Outlined.Verified,
                        stringResource(R.string.sold_by_bol_checkmark),
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Text(
                text = formatPrice(priceDetails.maxBid, "EUR"),
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 19.sp
            )
        }
        if (isFirst && isDividerVisible) {
            HorizontalDivider(
                color = MaterialTheme.colorScheme.onBackground,
                thickness = extraSmallPadding,
                modifier = Modifier.padding(vertical = extraSmallPadding)
            )
        }
    }
}