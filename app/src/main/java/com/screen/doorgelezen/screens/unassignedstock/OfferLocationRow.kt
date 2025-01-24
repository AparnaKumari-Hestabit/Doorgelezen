package com.screen.doorgelezen.screens.unassignedstock

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.screen.doorgelezen.ui.theme.offWhite

@Composable
fun OfferLocationRow(
//    offerLocation: OfferLocation,
    modifier: Modifier = Modifier,
//    additionalMetadata: String? = pluralStringResource(
//        com.screen.doorgelezen.R.plurals.books_at_location_format,
//        offerLocation.volume,
//        offerLocation.volume
//    ),
    content: @Composable() () -> Unit = {}
) {
    val paddingMedium = dimensionResource(com.screen.doorgelezen.R.dimen.padding_medium)
    val paddingSmall = dimensionResource(com.screen.doorgelezen.R.dimen.padding_small)
    val cornerRadius = 6
//    val offer = offerLocation.offer
    val metadataColor = Color.DarkGray
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(cornerRadius)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(color = offWhite)
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
//                    .data(offer.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(id = com.screen.doorgelezen.R.string.cover_image),
                contentScale = ContentScale.Fit,
                fallback = painterResource(id = com.screen.doorgelezen.R.drawable.bibliophile),
                placeholder = painterResource(id = com.screen.doorgelezen.R.drawable.bibliophile),
                modifier = Modifier
                    .padding(
                        start = paddingMedium,
                        top = paddingMedium,
                        end = paddingSmall,
                        bottom = paddingMedium
                    )
                    .widthIn(max = dimensionResource(com.screen.doorgelezen.R.dimen.book_cover_width))
                    .weight(1.0f)
            )
            Column(
                Modifier.padding(
                    start = paddingSmall,
                    top = paddingMedium,
                    end = paddingMedium,
                    bottom = paddingMedium
                )
            ) {
//                Text(
//                    text = offer.title,
//                    style = MaterialTheme.typography.titleLarge
//                )
                Row(
                    modifier = Modifier
                        .padding(top = paddingSmall),
                    verticalAlignment = Alignment.CenterVertically
                ) {
//                    Text(
//                        text = offer.ean,
//                        style = MaterialTheme.typography.labelSmall,
//                        color = metadataColor
//                    )
//                    if (!additionalMetadata.isNullOrBlank()) {
//                        Text(text = " - ", color = metadataColor)
//                    }
//                    Text(
//                        text = additionalMetadata ?: "",
//                        style = MaterialTheme.typography.labelSmall,
//                        color = metadataColor
//                    )
                }
                Row(
                    modifier = Modifier
                        .padding(top = paddingSmall),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
//                    Tag(stringResource(offer.condition.stringResourceId))
                    content()
                }
            }
        }
    }
}