package com.screen.doorgelezen.data.models

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.screen.doorgelezen.data.models.events.EventType
import java.util.UUID


data class InternalMessage(
    @StringRes private val localisedMessageResId: Int,
    val eventType: EventType,
    val id: UUID = UUID.randomUUID()
) {
    val localisedMessage: String
        @Composable
        get() = stringResource(localisedMessageResId)
}