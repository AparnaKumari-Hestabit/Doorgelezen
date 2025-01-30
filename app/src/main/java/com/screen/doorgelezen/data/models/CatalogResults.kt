package com.screen.doorgelezen.data.models

import com.google.gson.annotations.SerializedName

data class CatalogResults(

    @SerializedName("results") var results: ArrayList<BolProduct> = arrayListOf()

)