package com.stefick.zeljade.core.network.base

import com.stefick.zeljade.core.network.adapter.NetworkCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class BaseNetworkFactory<Api>(private val apiClass: Class<Api>) {

    protected val api: Api = createApiReference()

    protected abstract fun createOkhttp(): OkHttpClient

    private fun createApiReference(): Api =
        Retrofit.Builder()
            .baseUrl("https://botw-compendium.herokuapp.com/api/v3/compendium/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(apiClass)

}