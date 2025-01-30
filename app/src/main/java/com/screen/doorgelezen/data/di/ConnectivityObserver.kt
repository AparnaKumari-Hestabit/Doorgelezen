package com.screen.doorgelezen.data.di

import kotlinx.coroutines.flow.Flow


fun interface ConnectivityObserver {

    fun observe(): Flow<Status>

    enum class Status {
        AVAILABLE, UNAVAILABLE, LOSING, LOST
    }

}