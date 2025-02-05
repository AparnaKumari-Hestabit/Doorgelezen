package com.screen.doorgelezen.data.di

import com.screen.doorgelezen.data.api.AuthenticationAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class AuthenticationModule {
    @Provides
    fun provideAuthenticationService(retrofit: Retrofit): AuthenticationAPI {
        return retrofit.create(AuthenticationAPI::class.java)
    }
}