package com.screen.doorgelezen.screens.unassignedstock

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Tag(
    text: String,
    modifier: Modifier = Modifier,
    selected: Boolean = true,
    onSelect: (() -> Unit)? = null
) {
    val paddingSmall = dimensionResource(com.screen.doorgelezen.R.dimen.padding_small)
    val paddingMedium = dimensionResource(com.screen.doorgelezen.R.dimen.padding_medium)
    val cornerRadius = 24
    val shape = RoundedCornerShape(cornerRadius)
    val backgroundColor: Color
    val textColor: Color
    if (selected) {
        backgroundColor = MaterialTheme.colorScheme.secondary
        textColor = MaterialTheme.colorScheme.onSecondary
    } else {
        backgroundColor = Color.Transparent
        textColor = MaterialTheme.colorScheme.onBackground
    }

    Surface(
        modifier = modifier
            .selectable(selected, enabled = onSelect != null, onClick = onSelect ?: {})
            .border(1.dp, MaterialTheme.colorScheme.secondary, shape = shape),
        color = backgroundColor,
        shape = shape
    ) {
        Text(
            text,
            modifier = Modifier.padding(vertical = paddingSmall, horizontal = paddingMedium),
            color = textColor,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Preview
@Composable
fun TagPreview() {
    Tag("EXAMPLE")
}