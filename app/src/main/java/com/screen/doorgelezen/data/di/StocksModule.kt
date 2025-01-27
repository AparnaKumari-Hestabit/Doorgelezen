package com.screen.doorgelezen.data.di

import com.screen.doorgelezen.data.api.StockAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StocksModule {

    @Provides
    @Singleton
    fun providesStockService(retrofit: Retrofit): StockAPI{
        return retrofit.create(StockAPI::class.java)
    }

}