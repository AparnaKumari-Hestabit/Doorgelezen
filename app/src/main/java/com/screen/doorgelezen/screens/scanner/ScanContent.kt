package com.screen.doorgelezen.screens.scanner

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.screen.doorgelezen.R
import com.screen.doorgelezen.utils.printDebug
import com.screen.doorgelezen.viewModels.CatalogViewModel


@Composable
fun ScanContent(viewModel: CatalogViewModel) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    val mediumPadding = dimensionResource(R.dimen.padding_medium)

    val product = viewModel.selectedCatalog.collectAsState().value!!

    val isLoading by viewModel.isLoading.collectAsState()

    viewModel.setScanned(false)

    val titleFontSize = when {
        screenHeight < 600.dp -> 16.sp
        screenHeight < 720.dp -> 20.sp
        else -> 24.sp
    }

    val subtitleFontSize = when {
        screenHeight < 600.dp -> 12.sp
        screenHeight < 720.dp -> 16.sp
        else -> 19.sp
    }

    val dbResultsText = if (product.dbResults.isNullOrEmpty()) {
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

    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black.copy(0.5f))
                .zIndex(2f)
                .clickable(enabled = false) {}, contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color.White)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(mediumPadding)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.25f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    product.title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    fontSize = titleFontSize,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth(0.9f)
                )

                Text(
                    text = product.ean,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.W400,
                    letterSpacing = 1.5.sp,
                    color = Color.DarkGray,
                    fontSize = subtitleFontSize
                )

                Text(
                    dbResultsText,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleSmall,
                    fontSize = subtitleFontSize,
                    modifier = Modifier.fillMaxWidth(0.9f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight(0.3f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                val imageHeight = minOf(screenHeight * 0.35f, 300.dp)
                val imageWidth = minOf(imageHeight * 0.7f, 190.dp)

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(mediumImageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = stringResource(R.string.cover_image),
                    contentScale = ContentScale.Fit,
                    fallback = painterResource(id = R.drawable.bibliophile),
                    placeholder = painterResource(id = R.drawable.bibliophile),
                    modifier = Modifier
                        .size(width = imageWidth, height = imageHeight)
                        .clip(RoundedCornerShape(5.dp))
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                ScanConditionPicker(
                    calculated = product.calculated,
                    soldByBol = product.soldByBol
                )
            }
        }
    }
}