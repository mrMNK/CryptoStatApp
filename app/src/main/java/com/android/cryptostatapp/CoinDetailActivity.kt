package com.android.cryptostatapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)
        if(!intent.hasExtra(EXTRA_FROM_SYMBOL)){
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL)
        viewModel = ViewModelProviders.of(this)[CoinViewModel::class.java]
        if (fromSymbol != null) {
            viewModel.getDetailInfo(fromSymbol).observe(this, Observer {
                findViewById<TextView>(R.id.tvFromSymbol).text = it.fromSymbol
                findViewById<TextView>(R.id.tvToSymbol).text = it.toSymbol
                findViewById<TextView>(R.id.tvPrice).text = it.price.toString()
                findViewById<TextView>(R.id.tvMinPrice).text = it.low24hour.toString()
                findViewById<TextView>(R.id.tvMaxPrice).text = it.high24hour.toString()
                findViewById<TextView>(R.id.tvLastMarket).text = it.lastMarket.toString()
                findViewById<TextView>(R.id.tvLastUpdate).text = it.getFormattedTime()
                val ivLogoCoin = findViewById<ImageView>(R.id.ivLogoCoin)
                Picasso.get().load(it.getFullImageUrl()).into(ivLogoCoin)
            })
        }
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }
}