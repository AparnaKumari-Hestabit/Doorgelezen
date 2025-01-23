package com.screen.doorgelezen.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import okhttp3.HttpUrl
import java.math.BigDecimal
import java.util.UUID


@Entity
data class Offer(
    @Expose(serialize = false)
    val id: UUID = UUID.randomUUID(),
    @Expose
    @SerializedName("fulfilment_method")
    @ColumnInfo(name = "fulfilment_method")
    val fulfilmentMethod: FulfilmentMethod = FulfilmentMethod.RETAILER,
    @Expose
    @SerializedName("delivery_method")
    @ColumnInfo(name = "delivery_method")
    val deliveryMethod: DeliveryMethod,
    @Expose
    val condition: OfferCondition,
    @Expose
    @SerializedName("price_in_eur")
    @ColumnInfo(name = "price_in_eur")
    val priceInEur: BigDecimal,
    @Expose
    val ean: String,
    @Expose
    val title: String,
    @Expose
    @SerializedName("image_url")
    @ColumnInfo(name = "image_url")
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