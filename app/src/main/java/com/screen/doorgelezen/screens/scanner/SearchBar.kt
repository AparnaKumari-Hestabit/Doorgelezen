package com.screen.doorgelezen.screens.scanner

import android.view.ViewTreeObserver
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseInOutQuad
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.screen.doorgelezen.R
import com.screen.doorgelezen.utils.printDebug
import com.screen.doorgelezen.viewModels.CatalogViewModel

@Composable
fun SearchBar(
    search: (String) -> Unit,
    viewModel: CatalogViewModel
) {
    val paddingExtraSmall = dimensionResource(R.dimen.padding_small)
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    val query by viewModel.searchQuery.collectAsState()
    val searching by viewModel.isSearching.collectAsState()

    val isKeyboardVisible = isSoftwareKeyboardVisible()

    if(isKeyboardVisible){
//        viewModel.clearCatalog()
    }

    AnimatedVisibility(
        searching,
        enter = fadeIn(animationSpec = tween(durationMillis = 100, delayMillis = 100, easing = EaseInOutQuad)),
        exit = fadeOut(animationSpec = tween(durationMillis = 100, easing = EaseInOutQuad))
    ) {
        SearchField(
            query = query,
            onSearch = { newQuery ->
                viewModel.setQuery(newQuery)
                printDebug("query: $newQuery")
//                if (newQuery.isNotBlank()) {
//                    search(newQuery)
//                }
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingExtraSmall)
                .focusRequester(focusRequester),
            onClose = {
                focusManager.clearFocus()
                viewModel.setSearching(false)
                viewModel.setQuery()
                viewModel.clearCatalog()
            },
            keyboardActions = KeyboardActions(onSearch = {
                printDebug("query2: $query")
                if (query.isNotBlank()) {
                    search(query)
                }
                focusManager.clearFocus()
            })
        )
        LaunchedEffect(searching) {
            if(searching) {
                focusRequester.requestFocus()
            }
        }
    }

    AnimatedVisibility(
        !searching,
        enter = fadeIn(animationSpec = tween(durationMillis = 100, delayMillis = 100, easing = EaseInOutQuad)),
        exit = fadeOut(animationSpec = tween(durationMillis = 100, easing = EaseInOutQuad))
    ) {
        IconButton(onClick = {
            viewModel.setSearching(true)
        }) {
            Icon(
                Icons.Filled.Search,
                contentDescription = stringResource(R.string.search),
                tint = Color.White
            )
        }
    }
}

@Composable
fun isSoftwareKeyboardVisible(): Boolean {
    val view = LocalView.current
    val rootView = remember { view.rootView }
    var isKeyboardVisible by remember { mutableStateOf(false) }

    DisposableEffect(rootView) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = android.graphics.Rect()
            rootView.getWindowVisibleDisplayFrame(rect)
            val screenHeight = rootView.height
            val keypadHeight = screenHeight - rect.bottom
            isKeyboardVisible = keypadHeight > screenHeight * 0.15 // Keyboard is considered visible if height is significant
        }

        rootView.viewTreeObserver.addOnGlobalLayoutListener(listener)
        onDispose {
            rootView.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }

    return isKeyboardVisible
}