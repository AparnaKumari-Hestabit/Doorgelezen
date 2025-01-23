package com.screen.doorgelezen.data.models

import androidx.annotation.StringRes

data class LoginUIState(
    val data: AuthenticationData,
    @StringRes val emailErrorId: Int? = null,
    @StringRes val passwordErrorId: Int? = null
)