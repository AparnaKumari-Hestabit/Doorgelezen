package com.screen.doorgelezen.screens.scanner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import com.screen.doorgelezen.R
import com.screen.doorgelezen.data.models.BolProduct
import com.screen.doorgelezen.utils.printDebug

@Composable
fun SearchListing(results: List<BolProduct>, onNavigate: (BolProduct) -> Unit) {

    val text = if (results.size == 1) {
        stringResource(R.string.search_result)
    } else {
        stringResource(R.string.first_results, results.size)
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        val listModifier = if(results.size < 3){
            Modifier.height(130.dp)
        }else{
            Modifier.weight(1/(results.size).toFloat())
        }

        Column {
            Text(
                text = text,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 20.dp),
                fontSize = 17.sp
            )

            HorizontalDivider(
                color = Color.LightGray,
                thickness = 1.dp,
            )
        }
        Column {


            results.forEachIndexed { index, bolProduct ->
                SearchResultListItem(
                    modifier = listModifier,
                    product = bolProduct,
                    onClick = onNavigate
                )
                HorizontalDivider(
                    color = Color.LightGray,
                    thickness = 1.dp,
                )
            }
        }
    }
}
