package com.screen.doorgelezen.data.di

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ConnectivityModule {

    @Provides
    @Singleton
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Provides
    @Singleton
    fun provideConnectivityObserver(connectivityManager: ConnectivityManager): ConnectivityObserver {
        return NetworkConnectivityObserver(connectivityManager)
    }

    @Provides
    fun getCurrentConnectivityState(
        connectivityManager: ConnectivityManager
    ): ConnectivityObserver.Status {
        val connected = connectivityManager.allNetworks.any { network ->
            connectivityManager.getNetworkCapabilities(network)
                ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                ?: false
        }

        return if (connected) ConnectivityObserver.Status.AVAILABLE else ConnectivityObserver.Status.UNAVAILABLE
    }
}