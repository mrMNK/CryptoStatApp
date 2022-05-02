package com.android.cryptostatapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.cryptostatapp.R
import com.android.cryptostatapp.pojo.CoinPriceInfo
import com.squareup.picasso.Picasso

class CoinInfoAdapter(private val context: Context): RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {

    var coinInfoList: List<CoinPriceInfo> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    var onCoinClickListener: OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_coin_info, parent, false)
        return CoinInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinInfoList[position]
        with(holder) {
            with(coin) {
                val symbolsTemplate = context.resources.getString(R.string.symbols_template)
                val lastUpdateTemplate = context.resources.getString(R.string.last_update_template)
                tvSymbols.text = String.format(symbolsTemplate, fromSymbol, toSymbol)
                tvPrice.text = price.toString()
                tvLastUpdate.text = String.format(lastUpdateTemplate, getFormattedTime())
                Picasso.get().load(coin.getFullImageUrl()).into(holder.ivLogoCoin)
                itemView.setOnClickListener{
                    onCoinClickListener?.onCoinClick(this)
                }
            }
        }
    }

    override fun getItemCount() = coinInfoList.size

    inner class CoinInfoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val ivLogoCoin = itemView.findViewById<ImageView>(R.id.ivLogoCoin)
        val tvSymbols = itemView.findViewById<TextView>(R.id.tvSymbols)
        val tvPrice = itemView.findViewById<TextView>(R.id.tvPrice)
        val tvLastUpdate = itemView.findViewById<TextView>(R.id.tvLastUpdate)
    }

    interface OnCoinClickListener {
        fun onCoinClick(coinPriceInfo: CoinPriceInfo)
    }
}