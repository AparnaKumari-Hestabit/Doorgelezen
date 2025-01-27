package com.screen.doorgelezen.utils

import androidx.annotation.StringRes
import com.screen.doorgelezen.data.models.InternalMessage
import com.screen.doorgelezen.data.models.events.Event
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import com.screen.doorgelezen.R

@Suppress("MemberVisibilityCanBePrivate")
class EventManager @Inject constructor(
    private val internalMessageFlow: MutableSharedFlow<InternalMessage>,
    private val eventFlow: MutableSharedFlow<Event>
) {
    suspend fun emitEvent(event: Event) {
        eventFlow.emit(event)
    }

    suspend fun emitEvent(event: Event, @StringRes messageRes: Int) {
        emitEvent(event)
        internalMessageFlow.emit(InternalMessage(messageRes, event.type))
    }

    suspend fun emitError(
        throwable: Throwable,
        @StringRes messageRes: Int = R.string.generic_error
    ) {
        emitEvent(com.screen.doorgelezen.data.models.events.Error(throwable), messageRes)
    }

    suspend fun silentEmitError(throwable: Throwable) {
        emitEvent(com.screen.doorgelezen.data.models.events.Error(throwable))
    }
}