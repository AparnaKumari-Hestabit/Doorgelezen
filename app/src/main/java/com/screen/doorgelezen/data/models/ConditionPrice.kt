package com.screen.doorgelezen.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ConditionPrice(
    @Expose
    val price: Double,
    @Expose
    @SerializedName("max_bid")
    val maxBid: Double
)