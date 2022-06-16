package com.example.coinbase.features.home.usecase

import com.example.coinbase.api.Repository
import com.example.coinbase.model.Criptos
import com.example.coinbase.utils.ResponseApi

class HomeUseCase(
    private val repository: Repository
) {

    suspend fun getCoinBase(): ResponseApi {
        return when (val responseApi = repository.getCoinBase()) {
            is ResponseApi.Success -> {
                val data = responseApi.data as? Criptos
                val result = data?.data?.map {
                    it
                }
                ResponseApi.Success(result)
            }
            is ResponseApi.Error -> {
                responseApi
            }
        }
    }
}