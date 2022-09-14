package com.stefick.zeljade.core.network.base

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

abstract class NetworkFactory<Api>(apiClass: Class<Api>) : BaseNetworkFactory<Api>(apiClass) {

    override fun createOkhttp(): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

}