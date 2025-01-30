package com.screen.doorgelezen.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Calculated(
    @Expose
    @SerializedName("NEW")
    val newCondition: ConditionPrice?,
    @Expose
    @SerializedName("AS_NEW")
    val asNewCondition: ConditionPrice?,
    @Expose
    @SerializedName("GOOD")
    val goodCondition: ConditionPrice?
)