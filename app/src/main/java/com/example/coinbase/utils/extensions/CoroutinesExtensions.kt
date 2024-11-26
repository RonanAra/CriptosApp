package com.example.coinbase.utils.extensions

import com.example.coinbase.utils.exceptions.RetrofitException
import com.google.gson.JsonParseException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun <T> CoroutineScope.launchSuspendFun(
    blockToRun: suspend CoroutineScope.() -> T,
    onLoading: ((Boolean) -> Unit)? = null,
    onSuccess: ((T) -> Unit)? = null,
    onError: ((RetrofitException) -> Unit)? = null,
) {
    launch {
        onLoading?.invoke(true)
        try {
            val result = blockToRun()
            onLoading?.invoke(false)
            onSuccess?.invoke(result)
        } catch (ex: Exception) {
            onLoading?.invoke(false)
            onError?.invoke(ex.getRetrofitException())
        }
    }
}

fun Exception.getRetrofitException(): RetrofitException {
    val message = when (this) {
        is HttpException -> "${CoroutinesConstants.ERROR_UNKNOWN} ${this.message}"
        is IOException -> {
            when (this) {
                is UnknownHostException -> CoroutinesConstants.ERROR_UNKNOWN_HOST
                is SocketTimeoutException -> CoroutinesConstants.TIMEOUT_ERROR
                else -> CoroutinesConstants.ERROR_UNKNOWN
            }
        }
        is JsonParseException -> "${CoroutinesConstants.ERROR_PARSE} ${this.message}"
        else -> CoroutinesConstants.ERROR_UNKNOWN
    }
    return RetrofitException(
        message = message,
        exception = this
    )
}

object CoroutinesConstants {
    const val ERROR_UNKNOWN_HOST = "Rede Indisponível"
    const val ERROR_UNKNOWN = "Erro Desconhecido"
    const val TIMEOUT_ERROR = "Tempo Excedido."
    const val ERROR_PARSE = "JsonParseException"
}