package com.android.cryptostatapp.di

import android.app.Application
import com.android.cryptostatapp.data.database.AppDatabase
import com.android.cryptostatapp.data.database.CoinInfoDao
import com.android.cryptostatapp.data.network.ApiFactory
import com.android.cryptostatapp.data.network.ApiService
import com.android.cryptostatapp.data.repository.CoinRepositoryImpl
import com.android.cryptostatapp.domain.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindCoinRepository(imp: CoinRepositoryImpl): CoinRepository

    companion object {

        @Provides
        @ApplicationScope
        fun provideCoinInfoDao(
            application: Application
        ): CoinInfoDao {
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }

        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}