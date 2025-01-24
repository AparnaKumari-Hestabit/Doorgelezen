package com.screen.doorgelezen.screens.Authentication

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.hilt.navigation.compose.hiltViewModel
import com.screen.doorgelezen.R
import com.screen.doorgelezen.data.models.AuthModel
import com.screen.doorgelezen.utils.isValidEmail
import com.screen.doorgelezen.utils.printDebug
import com.screen.doorgelezen.viewModels.AuthViewModel

@Composable
fun AuthenticationScreen() {

    val extraLargePadding = dimensionResource(R.dimen.padding_extra_large)
    val cornerRadius = 2
    var loading: Boolean by remember { mutableStateOf(false) }


    BackHandler {
        //TODO : Do nothing on back
    }

    Column(
        modifier = Modifier.background(color = MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = if (loading) Arrangement.Center else Arrangement.Bottom
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(1f, false)
                .fillMaxWidth()
                .padding(extraLargePadding)
        ) {
            Image(
                painter = painterResource(id = R.drawable.bibliophile),
                contentDescription = stringResource(R.string.woman_reading_book)
            )
        }
        if (loading) CircularProgressIndicator(modifier = Modifier, color = Color.White)
        AnimatedVisibility(visible = !loading) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, false),
                shape = RoundedCornerShape(
                    topStartPercent = cornerRadius,
                    topEndPercent = cornerRadius
                )
            ) {
                LogInForm()
            }
        }
    }
}

@Composable
fun LogInForm(
) {
    val extraLargePadding = dimensionResource(R.dimen.padding_extra_large)
    val paddingMedium = dimensionResource(R.dimen.padding_medium)
    val paddingSmall = dimensionResource(R.dimen.padding_small)
    val paddingExtraSmall = dimensionResource(R.dimen.padding_extra_small)
    val buttonHeight = dimensionResource(R.dimen.button_height)

    val viewModel: AuthViewModel = hiltViewModel()
    val loginFlow = viewModel.loginFlow.collectAsState()
    printDebug(loginFlow.value.toString())

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val focusManager = LocalFocusManager.current
    val submitWrapper = {
        viewModel.login(AuthModel(email, password))
        focusManager.clearFocus()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(paddingExtraSmall),
        modifier = Modifier.padding(extraLargePadding),
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            singleLine = true,
            label = { Text(stringResource(R.string.email_address)) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) })
        )
        Text(
            text = "",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .padding(start = paddingMedium)
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = password,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = { password = it },
            singleLine = true,
            label = { Text(stringResource(R.string.password)) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { submitWrapper() })
        )
        Text(
            text = "",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .padding(start = paddingMedium)
                .fillMaxWidth()
        )
        Button(
            modifier = Modifier
                .padding(paddingSmall)
                .fillMaxWidth()
                .height(buttonHeight),
            onClick = submitWrapper,
            enabled = email.isNotEmpty() && password.isNotEmpty() && isValidEmail(email)
        ) {
            Text(stringResource(R.string.log_in))
        }
    }
}