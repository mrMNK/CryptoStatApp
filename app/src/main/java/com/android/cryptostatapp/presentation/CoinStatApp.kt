package com.android.cryptostatapp.presentation

import android.app.Application
import androidx.work.Configuration
import com.android.cryptostatapp.data.workers.CoinWorkerFactory
import com.android.cryptostatapp.di.DaggerApplicationComponent
import javax.inject.Inject

class CoinStatApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: CoinWorkerFactory

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }
}