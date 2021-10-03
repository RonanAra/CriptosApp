package com.example.coinbase.features.usecase

import com.example.coinbase.features.repository.HomeRepository
import com.example.coinbase.model.Criptos
import com.example.coinbase.utils.ResponseApi

class HomeUseCase {

    private val homeRepository = HomeRepository()

    suspend fun getCoinBase(): ResponseApi {
        return when (val responseApi = homeRepository.getCoinBase()) {
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