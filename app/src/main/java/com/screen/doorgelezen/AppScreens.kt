package com.screen.doorgelezen

enum class AppScreens(val route: String) {
    SPLASH("splashScreen"),
    AUTHENTICATION("authentication"),
    UNASSIGNEDSTOCK("Unassigned Stock"),
    SCANNER("Scanner"),

    SCAN_CONTENT("scan_content/{uuid}");

    companion object {
        fun createScanContentRoute(uuid: String) = "scan_content/$uuid"
    }
}
