package com.screen.doorgelezen.utils

import java.net.ConnectException
import javax.inject.Inject
import com.screen.doorgelezen.R

class ErrorManager @Inject constructor(private val eventManager: EventManager) {
    suspend fun emit(throwable: Throwable) {
        when (throwable) {
            is ConnectException -> eventManager.emitError(throwable, R.string.connection_error)
            is Localisable -> eventManager.emitError(throwable, throwable.res)
            else -> eventManager.emitError(throwable)
        }
    }

    suspend fun silentEmit(throwable: Throwable) {
        eventManager.silentEmitError(throwable)
    }
}
