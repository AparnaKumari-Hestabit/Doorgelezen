package com.screen.doorgelezen.utils

import android.content.Context
import com.screen.doorgelezen.data.models.Calculated
import com.screen.doorgelezen.data.models.ConditionPrice
import com.screen.doorgelezen.R

fun getConditionList(calculated: Calculated, context: Context): List<Pair<String, ConditionPrice>> {

    val conditionMap = mapOf(
        "New" to calculated.newCondition,
        "As New" to calculated.asNewCondition,
        "Good" to calculated.goodCondition
    )

    val conditionNames = mapOf(
        "New" to context.getString(R.string.new_),
        "As New" to context.getString(R.string.as_new),
        "Good" to context.getString(R.string.good)
    )

    return listOf("New", "As New", "Good")
        .mapNotNull { condition ->
            conditionMap[condition]?.let { condition to it }
        }
        .map { (condition, priceDetails) ->
            val localizedCondition = conditionNames[condition] ?: condition
            localizedCondition to priceDetails
        }
}

