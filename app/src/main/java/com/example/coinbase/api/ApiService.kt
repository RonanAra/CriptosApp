package com.example.coinbase.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {

    val coinApi: CoinApi = getCoinApiClient().create(CoinApi::class.java)

    fun getCoinApiClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.coinbase.com/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}