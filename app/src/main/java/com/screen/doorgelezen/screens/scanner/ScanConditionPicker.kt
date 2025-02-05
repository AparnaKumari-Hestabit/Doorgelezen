package com.screen.doorgelezen.screens.scanner

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.screen.doorgelezen.R
import com.screen.doorgelezen.data.models.Calculated
import com.screen.doorgelezen.utils.getConditionList

@Composable
fun ScanConditionPicker(
    calculated: Calculated?,
    soldByBol: Boolean
) {
    val smallPadding = dimensionResource(R.dimen.padding_small)
    val paddingExtraLarge = dimensionResource(R.dimen.padding_large)
    val context = LocalContext.current

    Column(
        Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(
            stringResource(R.string.cheapest_bol_offers),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = smallPadding),
            fontSize = 19.sp
        )

        if (calculated==null || calculated.asNewCondition==null || calculated.goodCondition==null || calculated.newCondition==null) {
            //no offer available
            Text(modifier = Modifier.padding(vertical = paddingExtraLarge),
                text = stringResource(R.string.no_bol_offers) + "\n" + stringResource(R.string.no_bol_offers_msg),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.W400,
                fontSize = 19.sp
            )
        } else {
            val conditionList = getConditionList(calculated, context)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = smallPadding),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = stringResource(id = R.string.max_bid),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
            //offer available
            LazyColumn {
                items(conditionList) { (condition, priceDetails) ->
                    ConditionItem(
                        condition = condition,
                        priceDetails = priceDetails,
                        soldByBol = soldByBol,
                        isFirst = condition == context.getString(R.string.new_),
                        isDividerVisible = conditionList.size > 1
                    )
                }
            }
        }
    }
}