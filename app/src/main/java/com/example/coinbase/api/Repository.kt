package com.example.coinbase.api

import com.example.coinbase.ApiService
import com.example.coinbase.base.BaseRepository
import com.example.coinbase.utils.ResponseApi

class Repository : BaseRepository() {

    suspend fun getCoinBase(): ResponseApi {
        return safeApiCall {
            ApiService.coinApi.getCoin()
        }
    }
}
