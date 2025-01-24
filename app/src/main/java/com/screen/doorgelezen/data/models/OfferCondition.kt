package com.screen.doorgelezen.data.models

import androidx.annotation.StringRes
import com.screen.doorgelezen.R

enum class OfferCondition(@StringRes val stringResourceId: Int) {
    GOOD(R.string.good), AS_NEW(R.string.as_new), NEW(
        R.string.new_
    );

    companion object {
        fun valueOf(value: String, default: OfferCondition?): OfferCondition? {
            return try {
                valueOf(value)
            } catch (e: IllegalArgumentException) {
                default
            }
        }
    }
}