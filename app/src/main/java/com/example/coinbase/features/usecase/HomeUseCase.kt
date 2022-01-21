package com.example.coinbase.features.usecase

import com.example.coinbase.api.Repository
import com.example.coinbase.model.Criptos
import com.example.coinbase.utils.ResponseApi

class HomeUseCase(
    private val repository: Repository
) {

    suspend fun getCoinBase(): ResponseApi {
        return when (val responseApi = repository.getCoinBase()) {
            is ResponseApi.Success -> {
                var data = responseApi.data as? Criptos
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