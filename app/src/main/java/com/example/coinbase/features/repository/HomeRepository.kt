package com.example.coinbase.features.repository

import com.example.coinbase.ApiService
import com.example.coinbase.base.BaseRepository
import com.example.coinbase.utils.ResponseApi

class HomeRepository : BaseRepository() {

    suspend fun getCoinBase(): ResponseApi {
        return safeApiCall {
            ApiService.coinApi.getCoin()
        }
    }
}
