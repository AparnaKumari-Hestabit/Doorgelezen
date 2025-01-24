package com.screen.doorgelezen.data.di

import com.screen.doorgelezen.data.repository.AuthRepository
import com.screen.doorgelezen.data.repository.AuthRepositoryImpl
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

}