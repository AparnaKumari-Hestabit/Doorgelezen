package com.screen.doorgelezen.screens.scanner

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.screen.doorgelezen.R
import com.screen.doorgelezen.data.models.BolProduct
import java.util.UUID

@Composable
fun SearchListing(results: List<BolProduct>, onNavigate: (UUID) -> Unit) {

    val text = if (results.size == 1) {
        stringResource(R.string.search_result)
    } else {
        stringResource(R.string.first_results, results.size)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            Text(
                text = text,
                style = MaterialTheme.typography.titleSmall.copy(fontSize = 15.sp),
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            )
            LazyColumn {
                items(results.size) { index ->
                    HorizontalDivider(
                        color = Color.LightGray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                    SearchResultListItem(product = results[index],
                        onClick = {
//                            onNavigate(AppScreens.ScanContent(results[index].uuid))

//                            navController.navigate("scanContent/${catalogResults[index].uuid}")

                            onNavigate(results[index].uuid)
                        })
                }
            }
        }
    }
}
