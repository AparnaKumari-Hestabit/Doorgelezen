package com.screen.doorgelezen.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun ScanContent(){

    val smallPadding = dimensionResource(com.screen.doorgelezen.R.dimen.padding_small)
    val mediumPadding = dimensionResource(com.screen.doorgelezen.R.dimen.padding_medium)
    val coverHeight = dimensionResource(com.screen.doorgelezen.R.dimen.scan_cover_height)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier.padding(mediumPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    "The Hitcheijekbgvkjdf ",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(
                        top = mediumPadding,
                        bottom = smallPadding
                    )
                )
                Text(
                    "489r83947t93857t953",
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    "324/43 CST",
                    style = MaterialTheme.typography.labelSmall
                )
                Column(
                    modifier = Modifier
                        .padding(mediumPadding)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
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
                    )
                }
            }
        }

        HorizontalDivider(
            modifier = Modifier.padding(vertical = smallPadding),
            color = Color.LightGray
        )

    }
}



@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Scan(){
    ScanContent()
}