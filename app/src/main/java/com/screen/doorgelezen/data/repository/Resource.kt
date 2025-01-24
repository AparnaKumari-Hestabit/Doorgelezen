package com.screen.doorgelezen.data.repository

import kotlin.Exception

sealed class Resource<out R> {
    data class Success<out R>(val result: R) : Resource<R>()
    data class Failure(val exception: Exception = Exception("cannot able to process request")) : Resource<Nothing>()
    data object Loading : Resource<Nothing>()
}