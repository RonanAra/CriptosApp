package com.example.coinbase.data.service

import com.example.coinbase.data.models.response.Criptos
import retrofit2.http.*

interface CoinApi {
    @GET("assets/info")
    suspend fun getCoinsList(): Criptos
}