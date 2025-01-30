package com.screen.doorgelezen.screens.scanner

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.screen.doorgelezen.R
import com.screen.doorgelezen.data.models.BolProduct

@Composable
fun ScanContent(
    productUuid: String
) {
    val smallPadding = dimensionResource(R.dimen.padding_small)
    val mediumPadding = dimensionResource(R.dimen.padding_medium)
    val coverHeight = dimensionResource(R.dimen.scan_cover_height)

    val catalogResults = remember { mutableStateListOf<BolProduct>() }

    val product = catalogResults.find { it.uuid == productUuid }

    product?.let { it ->
        val dbResultsText = if (it.dbResults.isEmpty()) {
            buildAnnotatedString {
                append(stringResource(id = R.string.no_sales_rank))
            }
        } else {
            buildAnnotatedString {
                append(
                    it.dbResults.joinToString(separator = ", ") { result ->
                        "${result.rank}/${result.count} ${result.category}"
                    }
                )
            }
        }

        val mediumImageUrl = it.assets
            .find { it.key == "medium" }
            ?.url

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(mediumPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.TopCenter),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    it.title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(
                        top = mediumPadding,
                        bottom = smallPadding
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    it.ean,
                    style = MaterialTheme.typography.labelSmall,
                    textAlign = TextAlign.Center
                )
                Text(
                    dbResultsText,
                    style = MaterialTheme.typography.labelSmall,
                    textAlign = TextAlign.Center
                )

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(mediumImageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = stringResource(R.string.cover_image),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(coverHeight)
                        .padding(
                            top = smallPadding,
                            bottom = mediumPadding
                        )
                        .align(Alignment.CenterHorizontally)
                )
            }

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = smallPadding)
                    .align(Alignment.BottomCenter),
                color = Color.LightGray
            )

            ScanConditionPicker(offers = it.offers, soldByBol = it.soldByBol)
        }
    }
}

