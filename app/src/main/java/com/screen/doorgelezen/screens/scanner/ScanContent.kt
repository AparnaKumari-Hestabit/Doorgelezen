package com.screen.doorgelezen.screens.scanner

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.screen.doorgelezen.R
import com.screen.doorgelezen.data.models.BolProduct
import com.screen.doorgelezen.data.repository.CatalogRepository.Companion.selectedProduct
import com.screen.doorgelezen.utils.printDebug
import com.screen.doorgelezen.viewModels.CatalogViewModel
import java.security.PrivateKey
import java.util.UUID

@Composable
fun ScanContent() {
    val smallPadding = dimensionResource(R.dimen.padding_small)
    val mediumPadding = dimensionResource(R.dimen.padding_medium)
    val coverHeight = dimensionResource(R.dimen.scan_cover_height)

    val product = selectedProduct

    val dbResultsText = if (product!!.dbResults.isEmpty()) {
        buildAnnotatedString {
            append(stringResource(id = R.string.no_sales_rank))
        }
    } else {
        buildAnnotatedString {
            append(
                product.dbResults.joinToString(separator = ", ") { result ->
                    "${result.rank}/${result.count} ${result.category}"
                }
            )
        }
    }
    val mediumImageUrl = product.assets
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
                product!!.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(
                    top = mediumPadding,
                    bottom = smallPadding
                ),
                textAlign = TextAlign.Center
            )
            Text(
                product!!.ean,
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
                    .data("https://2.img-dpreview.com/files/p/E~C1000x0S4000x4000T1200x1200~articles/3925134721/0266554465.jpeg")
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(R.string.cover_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(coverHeight)
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

        product.calculated?.let { it1 -> ScanConditionPicker(calculated = it1, soldByBol = product.soldByBol) }
    }
}

