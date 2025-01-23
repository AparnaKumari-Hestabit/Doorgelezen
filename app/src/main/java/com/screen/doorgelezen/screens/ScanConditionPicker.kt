package com.screen.doorgelezen.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign


@Composable
fun ScanConditionPicker(
) {
    val largePadding = dimensionResource(com.screen.doorgelezen.R.dimen.padding_large)
    val smallPadding = dimensionResource(com.screen.doorgelezen.R.dimen.padding_small)
    val extra_smallPadding = dimensionResource(com.screen.doorgelezen.R.dimen.padding_extra_small)


    Column(Modifier.fillMaxWidth()) {
        Text(
            stringResource(com.screen.doorgelezen.R.string.cheapest_bol_offers),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = smallPadding)
        )
            Text(
                stringResource(com.screen.doorgelezen.R.string.no_bol_offers),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = largePadding)
            )
                    Column {

                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(vertical = smallPadding)
                        ) {
                            Icon(
                                Icons.Outlined.Check,
                                stringResource(com.screen.doorgelezen.R.string.condition),
                                modifier = Modifier.padding(end = smallPadding)
                            )
                            Text(
                                text = stringResource(com.screen.doorgelezen.R.string.scan),
                            )
                                Icon(
                                    Icons.Outlined.Check,
                                    stringResource(com.screen.doorgelezen.R.string.sold_by_bol_checkmark),
                                    modifier = Modifier.padding(start = smallPadding)
                                )
                            }

                            Spacer(Modifier.weight(1f))
                            Text("23")
                        }
        HorizontalDivider(
            modifier = Modifier.padding(vertical = extra_smallPadding),
            thickness = extra_smallPadding,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}
