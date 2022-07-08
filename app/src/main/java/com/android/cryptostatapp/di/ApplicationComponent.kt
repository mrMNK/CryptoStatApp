package com.android.cryptostatapp.di

import android.app.Application
import com.android.cryptostatapp.presentation.CoinDetailFragment
import com.android.cryptostatapp.presentation.CoinPriceListActivity
import com.android.cryptostatapp.presentation.CoinStatApp
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class,
        WorkerModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: CoinPriceListActivity)

    fun inject(fragment: CoinDetailFragment)

    fun inject(application: CoinStatApp)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}