package dao

import AppScope
import me.tatarka.inject.annotations.Provides

interface DaoConfig {

    val dataFetcherDao: DataFetcherDao

    @AppScope
    @Provides
    fun provideDataFetcherDao(impl: DefaultDataFetcherDao): DataFetcherDao = impl

}