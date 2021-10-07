package com.example.coinbase.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
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
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "image_url")
    @SerializedName("image_url")
    val imageUrl: String,
    val listed: Boolean,
    @ColumnInfo(name = "name")
    val name: String,
    @SerializedName("price_alerts_enabled")
    val priceAlertsEnabled: Boolean,
    @SerializedName("recently_listed")
    val recentlyListed: Boolean,
    val slug: String,
    val supported: Boolean,
    @ColumnInfo(name = "symbol")
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
) : Parcelable {

        companion object{
            var DIFF_CALBACK: DiffUtil.ItemCallback<Data> =
                object  : DiffUtil.ItemCallback<Data>() {
                    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                            return oldItem.id == newItem.id
                    }

                    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                            return oldItem.id == newItem.id
                    }
                }

        }

}