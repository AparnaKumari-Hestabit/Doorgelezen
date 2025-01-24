package com.screen.doorgelezen.screens.scanner

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun ScanContent() {
    val smallPadding = dimensionResource(com.screen.doorgelezen.R.dimen.padding_small)
    val mediumPadding = dimensionResource(com.screen.doorgelezen.R.dimen.padding_medium)
    val coverHeight = dimensionResource(com.screen.doorgelezen.R.dimen.scan_cover_height)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.padding(mediumPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "The Hitcheijekbgvkjdf",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(
                        top = mediumPadding,
                        bottom = smallPadding
                    ),
                    textAlign = TextAlign.Center
                )
                Text(
                    "489r83947t93857t953",
                    style = MaterialTheme.typography.labelSmall,
                    textAlign = TextAlign.Center
                )
                Text(
                    "324/43 CST",
                    style = MaterialTheme.typography.labelSmall,
                    textAlign = TextAlign.Center
                )

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(null)
                        .crossfade(true)
                        .build(),
                    contentDescription = stringResource(com.screen.doorgelezen.R.string.cover_image),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(coverHeight)
                        .padding(
                            start = mediumPadding,
                            top = smallPadding,
                            end = mediumPadding,
                            bottom = mediumPadding
                        )
                        .align(Alignment.CenterHorizontally)
                )
            }
        }

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = smallPadding),
            color = Color.LightGray
        )
        //TODO
        //Conditions
        ScanConditionPicker()
    }
}
