package com.example.coinbase.utils

import retrofit2.Retrofit

inline fun <reified S> service(retrofit: Retrofit.Builder): Lazy<S> = lazy {
    retrofit.build().create(S::class.java)
}