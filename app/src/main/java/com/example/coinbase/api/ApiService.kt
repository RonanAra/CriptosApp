package com.example.coinbase



import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiService {

    val coinApi: CoinApi = getCoinApiClient().create(CoinApi::class.java)

    fun getCoinApiClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.coinbase.com/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }



}