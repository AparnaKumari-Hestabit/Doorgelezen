package com.screen.doorgelezen.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

@Entity
data class Location(
    @Expose
    @PrimaryKey
    val id: String,
    @Expose
    val volume: Int
) {
    companion object {
        const val DEFAULT_ID = "UNSET"
    }
}