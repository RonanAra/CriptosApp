package com.example.coinbase.domain.mapper

import com.example.coinbase.common.mapper.Mapper
import com.example.coinbase.data.models.response.CoinResponse
import com.example.coinbase.domain.entity.CoinModel

class CoinListMapperToModel : Mapper<CoinResponse, CoinModel> {
    override fun mapFrom(from: CoinResponse): CoinModel {
        with(from) {
            return CoinModel(
                color = color,
                id = id,
                imageUrl = imageUrl,
                name = name,
                symbol = symbol,
                website = website
            )
        }
    }
}