package com.screen.doorgelezen.data.di

import com.screen.doorgelezen.data.repository.AuthRepository
import com.screen.doorgelezen.data.repository.AuthRepositoryImpl
import com.screen.doorgelezen.data.repository.CatalogRepository
import com.screen.doorgelezen.data.repository.CatalogRepositoryImpl
import com.screen.doorgelezen.data.repository.StockRepository
import com.screen.doorgelezen.data.repository.StockRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideAuthRepository(impl: AuthRepositoryImpl):AuthRepository = impl

    @Provides
    @ViewModelScoped
    fun provideStockRepository(impl :StockRepositoryImpl) : StockRepository = impl

    @Provides
    @ViewModelScoped
    fun provideCatalogRepository(impl : CatalogRepositoryImpl): CatalogRepository = impl
}