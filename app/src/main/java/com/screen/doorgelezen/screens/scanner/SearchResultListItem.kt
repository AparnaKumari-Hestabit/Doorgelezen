package com.screen.doorgelezen.screens.scanner


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.screen.doorgelezen.data.models.BolProduct
import com.screen.doorgelezen.R


@Composable
fun SearchResultListItem(product: BolProduct, onClick: () -> Unit) {

    val mediumImageUrl = product.assets
        .find { it.key == "medium" }
        ?.url

    Row(
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = product.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                modifier = Modifier.padding(top = 3.dp),
                text = product.ean,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 11.sp)
            )
        }

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(mediumImageUrl)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(R.string.cover_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(80.dp)
                .padding(
                    3.dp
                )
        )
    }
}