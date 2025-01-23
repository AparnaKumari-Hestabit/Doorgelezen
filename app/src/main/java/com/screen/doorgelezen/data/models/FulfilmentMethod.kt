package com.screen.doorgelezen.data.models


enum class FulfilmentMethod(val acronym: String) {
    BOL("FBB"),
    RETAILER("FBR");

    companion object {
        private val acronymMap = entries.associateBy(FulfilmentMethod::acronym)
        fun fromAcronym(acronym: String) = acronymMap[acronym]
    }
}