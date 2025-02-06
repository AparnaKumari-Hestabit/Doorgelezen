package com.screen.doorgelezen.screens.scanner


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.screen.doorgelezen.data.models.BolProduct
import com.screen.doorgelezen.R
import com.screen.doorgelezen.utils.printDebug


@Composable
fun SearchResultListItem(modifier: Modifier = Modifier,product: BolProduct, onClick: (BolProduct) -> Unit) {

    val mediumImageUrl = product.assets
        .find { it.key == "medium" }
        ?.url

    Row(
        modifier = modifier
            .clickable {
                onClick(product)
            }
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = product.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 18.sp
            )
            Text(
                modifier = Modifier.padding(top = 5.dp),
                text = product.ean,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                fontWeight = FontWeight.W400,
                letterSpacing = 1.5.sp,
                color = Color.DarkGray
            )
        }

//        Spacer(modifier = Modifier.weight(1f))

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(mediumImageUrl)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(R.string.cover_image),
            contentScale = ContentScale.Crop,
            fallback = painterResource(id = R.drawable.bibliophile),
            placeholder = painterResource(id = R.drawable.bibliophile),
            modifier = Modifier
                .size(70.dp)
                .aspectRatio(3/4f)
                .padding(
                    3.dp
                )
                .clip(RoundedCornerShape(5.dp))
        )
    }
}