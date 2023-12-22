package com.example.coinbase.presentation.home.adapter

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coinbase.data.models.response.CoinResponse
import com.example.coinbase.databinding.CardItemBinding

class CoinViewHolder(private val binding: CardItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        coin: CoinResponse?,
        onItemClicked: (CoinResponse?) -> Unit
    ) {
        with(binding) {

            tvTitle.text = coin?.name
            tvCode.text = coin?.symbol
            cvWatch.setBackgroundColor(Color.parseColor(coin?.color))

            Glide
                .with(itemView.context)
                .load(coin?.imageUrl)
                .into(ivImage)

            cardItem.setOnClickListener {
                onItemClicked(coin)
            }
        }
    }
}