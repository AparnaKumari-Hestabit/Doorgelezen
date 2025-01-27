package com.screen.doorgelezen.data.di

import com.screen.doorgelezen.data.repository.AuthRepository
import com.screen.doorgelezen.data.repository.AuthRepositoryImpl
import com.screen.doorgelezen.data.repository.StockRepository
import com.screen.doorgelezen.data.repository.StockRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl):AuthRepository = impl

    @Provides
    fun provideStockRepository(impl :StockRepositoryImpl) : StockRepository = impl

}