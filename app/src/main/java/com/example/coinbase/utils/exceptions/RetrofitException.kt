package com.example.coinbase.utils.exceptions

class RetrofitException(
    message: String?,
    exception: Throwable?,
) : Throwable(message, exception)