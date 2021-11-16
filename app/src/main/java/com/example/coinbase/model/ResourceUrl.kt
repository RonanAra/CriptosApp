package com.example.coinbase.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ResourceUrl(
    @SerializedName("icon_url")
    val iconUrl: String,
    val link: String,
    val title: String,
    val type: String
)