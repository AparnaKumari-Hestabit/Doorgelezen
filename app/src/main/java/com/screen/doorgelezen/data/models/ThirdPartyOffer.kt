package com.screen.doorgelezen.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.math.BigDecimal
import java.util.UUID

data class ThirdPartyOffer(
    @Expose
    val id: UUID,
    @Expose
    val price: BigDecimal,
    @Expose
    val condition: OfferCondition,
    @Expose
    @SerializedName("retailer_id")
    val retailerId: Int
)