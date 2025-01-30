package com.screen.doorgelezen.screens.scanner

import android.hardware.lights.Light
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.TextFieldDefaults.TextFieldDecorationBox
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.textFieldWithoutLabelPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.screen.doorgelezen.R
import com.screen.doorgelezen.ui.theme.darkestGray
import com.screen.doorgelezen.ui.theme.green

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchField(
    query: String,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = stringResource(R.string.search),
    onClose: () -> Unit,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    val inputCornerRadius = dimensionResource(R.dimen.secondary_input_corner_radius)
    val interactionSource = remember { MutableInteractionSource() }

    BasicTextField(
        value = query,
        onValueChange = onSearch,
        modifier = modifier.background(
            color = MaterialTheme.colorScheme.primaryContainer,
            shape = RoundedCornerShape(inputCornerRadius)
        ).clip(RoundedCornerShape(inputCornerRadius)),
        singleLine = true,
        enabled = true,
        interactionSource = interactionSource,
        textStyle = TextStyle.Default.copy(color = Color.White),
        cursorBrush = SolidColor(Color.White),
        keyboardActions = keyboardActions
    ) {
        TextFieldDecorationBox(
            value = query,
            innerTextField = it,
            enabled = true,
            singleLine = true,
            visualTransformation = VisualTransformation.None,
            interactionSource = interactionSource,
            leadingIcon = {
                Icon(Icons.Filled.Search, stringResource(R.string.search), tint = Color.LightGray)
            },
            trailingIcon = {
                IconButton(onClick = {
                    onClose()
                }) {
                    Icon(Icons.Filled.Clear, "Close", tint = Color.LightGray)
                }
            },
            placeholder = { Text(placeholder, color = Color.LightGray) },
            contentPadding = textFieldWithoutLabelPadding(
                top = 0.dp,
                bottom = 0.dp
            )
        )
    }

}