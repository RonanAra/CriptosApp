package com.example.coinbase.presentation.home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coinbase.databinding.CardItemBinding
import com.example.coinbase.data.models.response.CoinResponse

class CoinAdapter(
    private val onItemClicked: (CoinResponse?) -> Unit
) : ListAdapter<CoinResponse, CoinAdapter.ViewHolder>(CoinResponse.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClicked)
    }

    class ViewHolder(private val binding: CardItemBinding) :
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
}