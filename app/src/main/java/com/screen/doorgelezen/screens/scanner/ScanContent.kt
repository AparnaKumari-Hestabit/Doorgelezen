package com.screen.doorgelezen.screens.scanner

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
fun ScanContent(viewModel: CatalogViewModel) {
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
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                product!!.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(
                    top = mediumPadding
                ),
                textAlign = TextAlign.Center,
                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                modifier = Modifier.padding(vertical = smallPadding),
                text = product.ean,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 17.sp),
                fontWeight = FontWeight.W400,
                letterSpacing = 1.5.sp,
                color = Color.DarkGray
            )
            Text(
                dbResultsText,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall.copy(fontSize = 19.sp),
            )

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(mediumImageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(R.string.cover_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(height = coverHeight, width = 190.dp)
                    .padding(
                        vertical = 20.dp
                    )
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(5.dp))
            )

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = smallPadding),
                color = Color.LightGray
            )

            ScanConditionPicker(calculated = product.calculated, soldByBol = product.soldByBol)
        }
    }
}