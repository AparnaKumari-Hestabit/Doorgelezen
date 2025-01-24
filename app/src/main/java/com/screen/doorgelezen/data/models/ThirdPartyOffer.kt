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
) : Comparable<ThirdPartyOffer> {
    override fun compareTo(other: ThirdPartyOffer): Int {
        val conditionOrder = condition.compareTo(other.condition)
        if (conditionOrder != 0) return conditionOrder

        return other.price.compareTo(price)
    }
}