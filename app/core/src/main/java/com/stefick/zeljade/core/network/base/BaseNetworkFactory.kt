package com.stefick.zeljade.core.network.base

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class BaseNetworkFactory<Api>(private val apiClass: Class<Api>) {

    protected val api: Api = createApiReference()

    protected abstract fun createOkhttp(): OkHttpClient

    private fun createApiReference(): Api =
        Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(apiClass)

}