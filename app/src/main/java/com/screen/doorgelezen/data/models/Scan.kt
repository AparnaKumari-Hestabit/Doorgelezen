package com.screen.doorgelezen.data.models

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import okhttp3.HttpUrl
import java.math.BigDecimal
import java.util.UUID

data class Scan(
    val title: String,
    val ean: String,
    val condition: OfferCondition = OfferCondition.AS_NEW,
    val imageUrl: HttpUrl? = null,
    val id: UUID = UUID.randomUUID(),
    val soldByBol: Boolean = false,
    val createdOn: Instant = Clock.System.now()
) {
    override fun equals(other: Any?): Boolean {
        if (other !is Scan) return false

        return other.hashCode() == hashCode()
    }

    override fun hashCode(): Int {
        return title.hashCode() * ean.hashCode()  * condition.hashCode() * (imageUrl?.hashCode()
            ?: 1) * id.hashCode()
    }
}