package com.screen.doorgelezen.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AuthenticationData(
    emailAddress: String,
    password: String
) {
    @Expose
    @SerializedName("email_address")
    val emailAddress: String = emailAddress.trim()

    @Expose
    val password: String = password.trim()

    val complete: Boolean
        get() = emailAddress.isNotBlank() && password.isNotBlank()

    fun withEmailAddress(emailAddress: String): AuthenticationData {
        return AuthenticationData(emailAddress, this.password)
    }

    fun withPassword(password: String): AuthenticationData {
        return AuthenticationData(this.emailAddress, password)
    }
}
