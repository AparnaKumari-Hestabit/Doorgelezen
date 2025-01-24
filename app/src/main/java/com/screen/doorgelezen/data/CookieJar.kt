package com.screen.doorgelezen.data

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences

class CookieJar(
    private val preferences: SharedPreferences
) {
    var cookies: Set<String>
        get() = preferences.getStringSet(PREFERENCE_KEY, null) ?: setOf()
        private set(value) = preferences
            .edit()
            .putStringSet(PREFERENCE_KEY, value)
            .apply()

    fun addCookies(cookies: Set<String>) {
        val existingCookies = this.cookies
        this.cookies = existingCookies + cookies
    }

    fun clear() {
        this.cookies = setOf()
    }

    companion object {
        private const val PREFERENCE_KEY = "cookies"

        fun createWithEncryptedPreferences(context: Context): CookieJar {
            return CookieJar(
                EncryptedSharedPreferences.create(
                    "cookies",
                    "cookies_key",
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                )
            )
        }
    }
}