package com.screen.doorgelezen.data.models

import com.google.gson.annotations.SerializedName

data class OfferLocation(
    @SerializedName("offer_id")
    val offerId: String,
    @SerializedName("location_id")
    val locationId: String,
    var volume: Int,
    val offer: Offer
)