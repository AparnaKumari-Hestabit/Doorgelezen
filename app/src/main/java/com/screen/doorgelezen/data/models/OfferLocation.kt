package com.screen.doorgelezen.data.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.UUID

@Entity(tableName = "offer_location", primaryKeys = ["offer_id", "location_id"])
data class OfferLocation(
    @Expose(serialize = false)
    @SerializedName("offer_id")
    @ColumnInfo(name = "offer_id")
    val offerId: UUID,
    @Expose(serialize = false)
    @SerializedName("location_id")
    @ColumnInfo(name = "location_id")
    val locationId: String,
    @Expose
    var volume: Int,
    @Expose(serialize = false)
    @Embedded
    val offer: Offer
)