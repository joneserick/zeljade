package com.stefick.zeljade.core.network.base

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import java.util.logging.Level

abstract class NetworkFactory<Api>(apiClass: Class<Api>) : BaseNetworkFactory<Api>(apiClass) {
    override fun createOkhttp(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }


}