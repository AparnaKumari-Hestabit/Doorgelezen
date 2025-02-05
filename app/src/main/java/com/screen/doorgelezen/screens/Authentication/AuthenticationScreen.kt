package com.screen.doorgelezen.screens.Authentication

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.screen.doorgelezen.AppScreens
import com.screen.doorgelezen.Doorgelezen.Companion.mIsConnected
import com.screen.doorgelezen.R
import com.screen.doorgelezen.data.models.AuthModel
import com.screen.doorgelezen.data.repository.Resource
import com.screen.doorgelezen.utils.isValidEmail
import com.screen.doorgelezen.utils.raiseToast
import com.screen.doorgelezen.viewModels.AuthViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AuthenticationScreen(onNavigate: (AppScreens) -> Unit) {

    val cornerRadius = 2

    val extraLargePadding = dimensionResource(R.dimen.padding_extra_large)
    val paddingMedium = dimensionResource(R.dimen.padding_medium)
    val paddingSmall = dimensionResource(R.dimen.padding_small)
    val buttonHeight = dimensionResource(R.dimen.button_height)
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()


    val viewModel: AuthViewModel = hiltViewModel()
    val loginFlow = viewModel.loginFlow.collectAsState()
    if (loginFlow.value == Resource.Success(Unit)) {
        onNavigate(AppScreens.SCANNER)
    }

    val loading by viewModel.isLoading.collectAsState()

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }
    val _context = LocalContext.current

    val focusManager = LocalFocusManager.current
    val submitWrapper = {
        if(mIsConnected) {
            viewModel.login(AuthModel(email, password)) {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message = _context.getString(R.string.credentials_error),
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }else{
            raiseToast(_context, _context.getString(R.string.NO_INTERNET_CONNECTION), Toast.LENGTH_SHORT)
        }
        focusManager.clearFocus()
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { _ ->

        Box {

            if (loading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .zIndex(2f)
                        .background(color = Color.Black.copy(0.5f)),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator(color = Color.White)
                }
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

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f, false),
                    shape = RoundedCornerShape(
                        topStartPercent = cornerRadius,
                        topEndPercent = cornerRadius
                    )
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(paddingMedium),
                        modifier = Modifier.fillMaxSize().padding(top = extraLargePadding).padding(horizontal = extraLargePadding),
                    ) {
                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            singleLine = true,
                            label = { Text(stringResource(R.string.email_address)) },
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                            keyboardActions = KeyboardActions(onNext = {
                                focusManager.moveFocus(
                                    FocusDirection.Down
                                )
                            })
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
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(buttonHeight),
                            onClick = submitWrapper,
                            shape = RoundedCornerShape(5.dp),
                            enabled = email.isNotEmpty() && password.isNotEmpty() && isValidEmail(
                                email
                            )
                        ) {
                            Text(stringResource(R.string.log_in))
                        }
                    }
                }
            }
        }
    }
}