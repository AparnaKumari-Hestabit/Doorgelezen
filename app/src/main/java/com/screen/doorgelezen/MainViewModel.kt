package com.screen.doorgelezen

import androidx.lifecycle.ViewModel
import com.screen.doorgelezen.data.CookieJar
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val cookieJar: CookieJar) : ViewModel(){

    val isAlreadyLoggedIn = cookieJar.cookies.isNotEmpty()

}