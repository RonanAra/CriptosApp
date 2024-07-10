package com.example.coinbase.utils

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
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
        if (this.isNotEmpty()) {
            Json.decodeFromString<R>(this)
        } else null
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

inline fun <reified T : Any> serializableType(
    isNullableAllowed: Boolean = false
) = object : NavType<T>(isNullableAllowed = isNullableAllowed) {
    override fun get(
        bundle: Bundle,
        key: String
    ): T? {
        return bundle.getString(key)?.let<String, T>(Json::decodeFromString)
    }

    override fun parseValue(value: String): T = Json.decodeFromString<T>(value)

    override fun serializeAsValue(value: T): String = Json.encodeToString(value)

    override fun put(
        bundle: Bundle,
        key: String,
        value: T
    ) {
        bundle.putString(
            key,
            Json.encodeToString(value)
        )
    }
}