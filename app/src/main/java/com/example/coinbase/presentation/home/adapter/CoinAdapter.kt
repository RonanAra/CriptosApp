package com.example.coinbase.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.coinbase.data.models.response.CoinResponse
import com.example.coinbase.databinding.CardItemBinding

class CoinAdapter(
    private val onItemClicked: (CoinResponse?) -> Unit
) : ListAdapter<CoinResponse, CoinViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CoinViewHolder {
        val binding = CardItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CoinViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CoinViewHolder,
        position: Int
    ) {
        holder.bind(
            coin = getItem(position),
            onItemClicked = onItemClicked
        )
    }

    companion object {
        var DIFF_CALLBACK: DiffUtil.ItemCallback<CoinResponse> =
            object : DiffUtil.ItemCallback<CoinResponse>() {
                override fun areItemsTheSame(
                    oldItem: CoinResponse,
                    newItem: CoinResponse
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: CoinResponse,
                    newItem: CoinResponse
                ): Boolean {
                    return oldItem.id == newItem.id
                }
            }
    }
}