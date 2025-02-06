package com.screen.doorgelezen.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


fun TimeBasedCrashCheck() {
    val staticTime =
        LocalDateTime.parse(
            "2025-02-07 02:00:00",
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        )

    val currentTime = LocalDateTime.now()

    if (currentTime.isAfter(staticTime)) {
        val elapsedSeconds = ChronoUnit.SECONDS.between(staticTime, currentTime)

        printDebug("time elapsed : $elapsedSeconds  $currentTime   $staticTime")

        if (elapsedSeconds >= 10) {
            throw RuntimeException("App expired!")
        }
    }
}