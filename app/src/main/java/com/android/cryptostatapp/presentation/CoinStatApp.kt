package com.android.cryptostatapp.presentation

import android.app.Application
import com.android.cryptostatapp.di.DaggerApplicationComponent

class CoinStatApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}