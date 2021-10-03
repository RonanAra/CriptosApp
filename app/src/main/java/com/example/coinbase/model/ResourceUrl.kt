package com.example.coinbase.model

import com.google.gson.annotations.SerializedName

data class ResourceUrl(
    @SerializedName("icon_url")
    val iconUrl: String,
    val link: String,
    val title: String,
    val type: String
)