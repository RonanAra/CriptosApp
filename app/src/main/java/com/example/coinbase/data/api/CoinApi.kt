package com.example.coinbase.data.api

import com.example.coinbase.data.model.Criptos
import retrofit2.http.*

interface CoinApi {
    @GET("assets/info")
    suspend fun getCoinsList(): Criptos
}