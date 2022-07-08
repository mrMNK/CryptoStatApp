package com.android.cryptostatapp.presentation

import android.app.Application
import androidx.work.Configuration
import com.android.cryptostatapp.data.database.AppDatabase
import com.android.cryptostatapp.data.mapper.CoinMapper
import com.android.cryptostatapp.data.network.ApiFactory
import com.android.cryptostatapp.data.workers.RefreshDataWorkerFactory
import com.android.cryptostatapp.di.DaggerApplicationComponent

class CoinStatApp : Application(), Configuration.Provider {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(
                RefreshDataWorkerFactory(
                    AppDatabase.getInstance(this).coinPriceInfoDao(),
                    ApiFactory.apiService,
                    CoinMapper()
                )
            )
            .build()
    }
}