package com.screen.doorgelezen.data.models

import com.google.gson.annotations.SerializedName

data class AuthModel(
    @SerializedName("email_address")val email: String,
    @SerializedName("password")val password:String)
