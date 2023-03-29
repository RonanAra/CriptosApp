package com.example.coinbase.api

import com.example.coinbase.model.Criptos
import retrofit2.http.*

interface CoinApi {
    @GET("assets/info")
    suspend fun getCoinsList(): Criptos
}