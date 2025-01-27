package com.screen.doorgelezen.data.models

sealed class ApiResponse {
    data class Success<T>(val data: T) : ApiResponse()
    data class Error(val message: String) : ApiResponse()
}