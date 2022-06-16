package com.example.coinbase.features.home.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coinbase.databinding.CardItemBinding
import com.example.coinbase.model.Data

class CoinAdapter(
    private val onItemClicked: (Data?) -> Unit
) : ListAdapter<Data, CoinAdapter.ViewHolder>(Data.DIFF_CALLBACK) {

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


    class ViewHolder(val binding: CardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(criptos: Data?, onItemClicked: (Data?) -> Unit) {
            with(binding) {

                tvTitle.text = criptos?.name
                tvCode.text = criptos?.symbol
                cvWatch.setBackgroundColor(Color.parseColor(criptos?.color))

                Glide
                    .with(itemView.context)
                    .load(criptos?.imageUrl)
                    .into(ivImage)

                cardItem.setOnClickListener {
                    onItemClicked(criptos)
                }
            }
        }
    }
}







