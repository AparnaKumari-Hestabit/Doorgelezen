package com.screen.doorgelezen.data.models

import com.google.gson.annotations.Expose


data class DbResult(
    @Expose
    val id: Int,

    @Expose
    val isbn: String,

    @Expose
    val rank: Int,

    @Expose
    val count: Int,

    @Expose
    val category: String
)