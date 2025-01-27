package com.screen.doorgelezen.utils

import android.util.Log
import com.screen.doorgelezen.data.CookieJar

fun printDebug(value:String){
    Log.d("DEBUG", "printDebug: $value")
}

fun printError(value:String){
    Log.e("DEBUG", "printDebug: $value")
}

fun isValidEmail(email: String): Boolean {
    val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    return email.matches(emailRegex.toRegex())
}
