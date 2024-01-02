package com.example.coinbase.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

fun String.encodeUrl(): String {
    return try {
        URLEncoder.encode(
            this,
            StandardCharsets.UTF_8.toString()
        )
    } catch (e: UnsupportedEncodingException) {
        ""
    }
}

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