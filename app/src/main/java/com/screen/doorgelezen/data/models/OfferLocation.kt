package com.screen.doorgelezen.data.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class OfferLocation(
    @SerializedName("offer_id")
    val offerId: String,
    @SerializedName("location_id")
    val locationId: String,
    var volume: Int,
    val offer: Offer
)