package com.screen.doorgelezen.screens.scanner

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.screen.doorgelezen.R

@Composable
fun SearchField(
    query: String,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = stringResource(R.string.search),
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    val inputCornerRadius = dimensionResource(R.dimen.secondary_input_corner_radius)
    val interactionSource = remember { MutableInteractionSource() }

    TextField(
        value = query,
        onValueChange = onSearch,
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(inputCornerRadius)
            ),
        singleLine = true,
        enabled = true,
        interactionSource = interactionSource,
        textStyle = TextStyle.Default.copy(color = Color.White),
        keyboardActions = keyboardActions,
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = stringResource(R.string.search)
            )
        },
        placeholder = {
            Text(text = placeholder)
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(inputCornerRadius)
    )
}