package com.screen.doorgelezen.utils

import java.text.NumberFormat
import java.util.Currency
import java.util.Locale


fun formatPrice(price: Double, currencyCode: String): String {
    val currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
    currencyFormat.currency = Currency.getInstance(currencyCode)
    return currencyFormat.format(price)
}