package com.example.coinbase.utils

import android.net.Uri
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

inline fun <reified T> T.encodeObjectToArgs(): String {
    return try {
        Uri.encode(Json.encodeToString(this))
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}

inline fun <reified R> String.decodeObjectToArgs(): R? {
    return try {
        Json.decodeFromString<R>(this)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}