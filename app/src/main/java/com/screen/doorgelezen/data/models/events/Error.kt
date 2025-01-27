package com.screen.doorgelezen.data.models.events


data class Error(val throwable: Throwable) : Event(EventType.ERROR)