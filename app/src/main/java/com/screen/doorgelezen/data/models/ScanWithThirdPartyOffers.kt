package com.screen.doorgelezen.data.models

data class ScanWithThirdPartyOffers(
    val scan: Scan,
    val offers: List<ThirdPartyOffer>,
    val dbResult: List<DbResult>,
    val assets: List<Assets>
)