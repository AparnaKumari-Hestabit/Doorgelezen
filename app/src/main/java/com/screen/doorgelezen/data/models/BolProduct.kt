package com.screen.doorgelezen.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import okhttp3.HttpUrl

data class BolProduct(
    @Expose
    val ean: String,
    @Expose
    val title: String,
    @Expose
    @SerializedName("image_url")
    val imageURL: HttpUrl?,
    @Expose
    val offers: List<ThirdPartyOffer>,
    @Expose
    @SerializedName("db_results")
    val dbResults: List<DbResult>,
    @Expose
    @SerializedName("sold_by_bol")
    val soldByBol: Boolean,
    @Expose
    @SerializedName("assets")
    val assets: List<Assets>
)