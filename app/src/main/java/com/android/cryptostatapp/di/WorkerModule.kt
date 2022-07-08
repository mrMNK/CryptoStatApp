package com.android.cryptostatapp.di

import androidx.work.ListenableWorker
import com.android.cryptostatapp.data.workers.ChildWorkerFactory
import com.android.cryptostatapp.data.workers.RefreshDataWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(RefreshDataWorker::class)
    fun bindRefreshDataWorkerFactory(worker: RefreshDataWorker.Factory): ChildWorkerFactory
}