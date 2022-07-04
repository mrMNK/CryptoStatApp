package com.android.cryptostatapp.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.android.cryptostatapp.R
import com.android.cryptostatapp.data.network.ApiFactory.BASE_IMAGE_URL
import com.android.cryptostatapp.utils.convertTimestampToTime
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)
        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL) ?: EMPTY_SYMBOL
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        if (fromSymbol != null) {
            viewModel.getDetailInfo(fromSymbol).observe(this) {
                findViewById<TextView>(R.id.tvFromSymbol).text = it.fromSymbol
                findViewById<TextView>(R.id.tvToSymbol).text = it.toSymbol
                findViewById<TextView>(R.id.tvPrice).text = it.price.toString()
                findViewById<TextView>(R.id.tvMinPrice).text = it.lowDay.toString()
                findViewById<TextView>(R.id.tvMaxPrice).text = it.highDay.toString()
                findViewById<TextView>(R.id.tvLastMarket).text = it.lastMarket
                findViewById<TextView>(R.id.tvLastUpdate).text =
                    convertTimestampToTime(it.lastUpdate)
                val ivLogoCoin = findViewById<ImageView>(R.id.ivLogoCoin)
                Picasso.get().load(BASE_IMAGE_URL + it.imageUrl).into(ivLogoCoin)
            }
        }
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"
        private const val EMPTY_SYMBOL = ""

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }
}