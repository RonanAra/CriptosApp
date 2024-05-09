package com.example.coinbase.utils

import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun <T> CoroutineScope.launchSuspendFun(
    blockToRun: suspend CoroutineScope.() -> T,
    onLoading: ((Boolean) -> Unit)? = null,
    onSuccess: ((T) -> Unit)? = null,
    onError: ((Throwable) -> Unit)? = null,
) {
    launch {
        onLoading?.invoke(true)
        try {
            val result = blockToRun()
            onLoading?.invoke(false)
            onSuccess?.invoke(result)
        } catch (ex: Exception) {
            onLoading?.invoke(false)
            onError?.invoke(ex)
        }
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

    override fun parseValue(value: String): T = value.decodeObjectToArgs<T>()!!

    override fun serializeAsValue(value: T): String = value.encodeObjectToArgs()

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