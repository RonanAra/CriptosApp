package com.example.coinbase.utils

import android.net.Uri
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException

fun <T> T.encodeObjectToArgs(): String {
    return try {
        Uri.encode(Gson().toJson(this))
    } catch (_: RuntimeException) {
        ""
    }
}

inline fun <reified R> String?.decodeObjectToArgs(): R? {
    return try {
        if (!this.isNullOrEmpty()) {
            Gson().fromJson(this, R::class.java)
        } else null
    } catch (e: JsonSyntaxException) {
        e.printStackTrace()
        null
    }
}