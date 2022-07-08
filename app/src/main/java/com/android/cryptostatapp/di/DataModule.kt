package com.android.cryptostatapp.di

import android.app.Application
import com.android.cryptostatapp.data.database.AppDatabase
import com.android.cryptostatapp.data.database.CoinInfoDao
import com.android.cryptostatapp.data.repository.CoinRepositoryImpl
import com.android.cryptostatapp.domain.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindCoinRepository(imp: CoinRepositoryImpl): CoinRepository

    companion object {

        @Provides
        fun provideCoinInfoDao(
            application: Application
        ): CoinInfoDao {
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }
    }
}