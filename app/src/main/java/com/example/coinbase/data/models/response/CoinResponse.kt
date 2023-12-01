package com.example.coinbase.data.models.response

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CoinResponse(
    val color: String,
    val id: String,
    @SerializedName("image_url")
    val imageUrl: String,
    val name: String,
    val symbol: String,
    val website: String,
) : Serializable  {

    companion object {
        var DIFF_CALLBACK: DiffUtil.ItemCallback<CoinResponse> =
            object : DiffUtil.ItemCallback<CoinResponse>() {
                override fun areItemsTheSame(oldItem: CoinResponse, newItem: CoinResponse): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: CoinResponse, newItem: CoinResponse): Boolean {
                    return oldItem.id == newItem.id
                }
            }
    }
}