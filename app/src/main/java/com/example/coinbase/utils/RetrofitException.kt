package com.example.coinbase.utils

class RetrofitException(
    message: String?,
    exception: Throwable?,
) : Throwable(message, exception)