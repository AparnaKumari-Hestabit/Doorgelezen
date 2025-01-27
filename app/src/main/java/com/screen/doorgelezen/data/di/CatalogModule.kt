package com.screen.doorgelezen.data.di

import com.screen.doorgelezen.data.api.CatalogAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class CatalogModule {
    @Provides
    fun provideCatalogService(retrofit: Retrofit): CatalogAPI {
        return retrofit.create(CatalogAPI::class.java)
    }
}