package com.screen.doorgelezen.data.models

data class NoProductsFoundError(val query: String) : RuntimeException()