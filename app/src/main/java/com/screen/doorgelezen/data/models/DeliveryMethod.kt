package com.screen.doorgelezen.data.models

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.screen.doorgelezen.R

enum class DeliveryMethod(
    val code: String,
    @StringRes private val resourceId: Int,
    private vararg val formatArgs: Any
) {
    NEXT_DAY_BEFORE_ELEVEN("24uurs-23", R.string.next_day_delivery, 23),
    NEXT_DAY_BEFORE_TEN("24uurs-22", R.string.next_day_delivery, 22),
    NEXT_DAY_BEFORE_NINE("24uurs-21", R.string.next_day_delivery, 21),
    NEXT_DAY_BEFORE_EIGHT("24uurs-20", R.string.next_day_delivery, 20),
    NEXT_DAY_BEFORE_SEVEN("24uurs-19", R.string.next_day_delivery, 19),
    NEXT_DAY_BEFORE_SIX("24uurs-18", R.string.next_day_delivery, 18),
    NEXT_DAY_BEFORE_FIVE("24uurs-17", R.string.next_day_delivery, 17),
    NEXT_DAY_BEFORE_FOUR("24uurs-16", R.string.next_day_delivery, 16),
    NEXT_DAY_BEFORE_THREE("24uurs-15", R.string.next_day_delivery, 15),
    NEXT_DAY_BEFORE_TWO("24uurs-14", R.string.next_day_delivery, 14),
    NEXT_DAY_BEFORE_ONE("24uurs-13", R.string.next_day_delivery, 13),
    NEXT_DAY_BEFORE_TWELVE("24uurs-12", R.string.next_day_delivery, 12),
    ONE_TO_TWO_DAYS("1-2d", R.string.day_range, 1, 2),
    TWO_TO_THREE_DAYS("2-3d", R.string.day_range, 2, 3),
    THREE_TO_FIVE_DAYS("3-5d", R.string.day_range, 3, 5),
    FOUR_TO_EIGHT_DAYS("4-8d", R.string.day_range, 4, 8),
    ONE_TO_EIGHT_DAYS("1-8d", R.string.day_range, 1, 8),
    CUSTOM("MijnLeverbelofte", R.string.custom_delivery_method),
    BOL("VVB", R.string.bol_delivery_method);

    @Composable
    fun localised(): String = stringResource(resourceId, *formatArgs)

    companion object {
        private val codeMap = entries.associateBy(DeliveryMethod::code)
        val reversedValues by lazy { entries.reversed() }

        fun fromCode(code: String) = codeMap[code]
    }
}