package com.example.coinbase.model

import com.google.gson.annotations.SerializedName


data class Data(
    @SerializedName("address_regex")
    val addressRegex: String,
    @SerializedName("asset_type")
    val assetType: String,
    @SerializedName("asset_type_description")
    val assetTypeDescription: String?,
    val color: String,
    @SerializedName("contract_address")
    val contractAddress: String,
    val description: String,
    val exponent: Int,
    val id: String,
    @SerializedName("image_url")
    val imageUrl: String,
    val listed: Boolean,
    val name: String,
    @SerializedName("price_alerts_enabled")
    val priceAlertsEnabled: Boolean,
    @SerializedName("recently_listed")
    val recentlyListed: Boolean,
    val slug: String,
    val supported: Boolean,
    val symbol: String,
    @SerializedName("tradable_on_wallet")
    val tradableOnWallet: Boolean,
    @SerializedName("transaction_unit_price_scale")
    val transactionUnitPriceScale: Int,
    @SerializedName("unit_price_scale")
    val unitPriceScale: Int,
    @SerializedName("uri_scheme")
    val uriScheme: String,
    val website: String,
    @SerializedName("white_paper")
    val whitePaper: String?
)