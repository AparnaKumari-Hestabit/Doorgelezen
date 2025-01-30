package com.screen.doorgelezen.screens.scanner

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.screen.doorgelezen.R

@Composable
fun SearchBar(
    search: (String) -> Unit
) {
    val paddingExtraSmall = dimensionResource(R.dimen.padding_small)
    val focusRequester = remember { FocusRequester() }
    var query by remember { mutableStateOf("") }
    var searching by remember { mutableStateOf(false) }

    if (searching) {
        SearchField(
            query = query,
            onSearch = { newQuery ->
                query = newQuery
                if (query.isNotBlank()) {
                search(query)
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingExtraSmall)
                .focusRequester(focusRequester),
            onClose = {
                focusRequester.freeFocus()
                searching = false
                query = ""},
            keyboardActions = KeyboardActions(onDone = {
                focusRequester.freeFocus()
                searching = false
                query = ""
            })
        )
        LaunchedEffect(searching) {
            focusRequester.requestFocus()
        }
    } else {
        IconButton(onClick = { searching = true }) {
            Icon(
                Icons.Filled.Search,
                contentDescription = stringResource(R.string.search),
                tint = Color.White
            )
        }
    }
}