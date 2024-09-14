package com.stefick.zeljade.core.di.base

import com.stefick.zeljade.core.api.CompendiumAPI
import com.stefick.zeljade.core.constants.DEFAULT_READ_TIMEOUT_VALUE
import com.stefick.zeljade.core.constants.DEFAULT_WRITE_TIMEOUT_VALUE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BaseModule {

    @Provides
    @Singleton
    fun provideCompendiumApi(okHttpClient: OkHttpClient): CompendiumAPI {
        return Retrofit.Builder()
            .baseUrl("https://botw-compendium.herokuapp.com/api/v3/compendium/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(CompendiumAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideOKHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .readTimeout(DEFAULT_READ_TIMEOUT_VALUE, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_WRITE_TIMEOUT_VALUE, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()
    }
}