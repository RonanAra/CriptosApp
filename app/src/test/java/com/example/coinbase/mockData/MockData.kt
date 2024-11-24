package com.example.coinbase.mockData

import com.example.coinbase.domain.entity.CoinModel
import com.example.coinbase.utils.RetrofitException

object MockData {
    val coins = listOf(
        CoinModel(
            color = "#F7931A",
            id = "5b71fc48-3dd3-540c-809b-f8c94d0e68b5",
            imageUrl = "https://dynamic-assets.coinbase.com/" +
                    "e785e0181f1a23a30d9476038d9be91e9f6c63959b538eabbc51a1abc889" +
                    "8940383291eede695c3b8dfaa1829a9b57f5a2d0a16b0523580346c6b8fab6" +
                    "7af14b/asset_icons/b57ac673f06a4b0338a596817eb0a50ce16e2059f327dc117744449a47915cb2.png",
            name = "Bitcoin",
            symbol = "BTC",
            website = "https://bitcoin.org"
        ),
        CoinModel(
            color = "#F7931A",
            id = "5b71fc48-3dd3-540c-809b-f8c94d0e68b5",
            imageUrl = "https://dynamic-assets.coinbase.com/" +
                    "e785e0181f1a23a30d9476038d9be91e9f6c63959b538eabbc51a1abc889" +
                    "8940383291eede695c3b8dfaa1829a9b57f5a2d0a16b0523580346c6b8fab6" +
                    "7af14b/asset_icons/b57ac673f06a4b0338a596817eb0a50ce16e2059f327dc117744449a47915cb2.png",
            name = "Bitcoin",
            symbol = "BTC",
            website = "https://bitcoin.org"
        )
    )

    val errorException = RetrofitException("Erro Desconhecido", null)
}