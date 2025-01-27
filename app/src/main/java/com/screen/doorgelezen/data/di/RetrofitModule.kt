package com.screen.doorgelezen.data.di

import android.content.Context
import com.screen.doorgelezen.data.CookieJar
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import doorgelezen.lib.network.interceptors.ReceiveCookiesInterceptor
import doorgelezen.lib.network.interceptors.SendCookiesInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun provideBaseUrl(): String = "https://doorgelezen-stag.hestawork.com"

    @Singleton
    @Provides
    fun provideCookieJar(@ApplicationContext context: Context): CookieJar {
        return CookieJar.createWithEncryptedPreferences(context)
    }

    @Singleton
    @Provides
    fun provideHttpClient(
        sendCookiesInterceptor: SendCookiesInterceptor,
        receiveCookiesInterceptor: ReceiveCookiesInterceptor
    ): OkHttpClient {
        val timeout = 30L
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(sendCookiesInterceptor)
            .addInterceptor(receiveCookiesInterceptor)
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        httpClient: OkHttpClient,
        baseUrl: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}