package com.screen.doorgelezen.data.models


import com.google.gson.annotations.SerializedName
import doorgelezen.models.DeliveryMethod
import doorgelezen.models.FulfilmentMethod
import okhttp3.HttpUrl
import java.math.BigDecimal
import java.util.*

data class Offer(
    val id: UUID = UUID.randomUUID(),
    @SerializedName("fulfilment_method")
    val fulfilmentMethod: FulfilmentMethod = FulfilmentMethod.RETAILER,
    @SerializedName("delivery_method")
    val deliveryMethod: DeliveryMethod,
    val condition: OfferCondition,
    @SerializedName("price_in_eur")
    val priceInEur: BigDecimal,
    val ean: String,
    val title: String,
    @SerializedName("image_url")
    val imageUrl: HttpUrl? = null
) {
    companion object {
        fun fromScan(
            scan: Scan,
            priceInEur: BigDecimal
        ): Offer {
            return Offer(
                deliveryMethod = DeliveryMethod.ONE_TO_TWO_DAYS,
                condition = scan.condition,
                priceInEur = priceInEur,
                ean = scan.ean,
                title = scan.title,
                imageUrl = scan.imageUrl
            )
        }
    }
}