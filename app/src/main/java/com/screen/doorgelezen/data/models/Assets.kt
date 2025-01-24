package com.screen.doorgelezen.data.models

import com.google.gson.annotations.Expose

data class Assets(
    @Expose
    val url : String,

    @Expose
    val mime_type : String,

    @Expose
    val key : String
)