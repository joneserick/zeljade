package com.stefick.zeljade.core.network.adapter

import com.stefick.zeljade.core.network.base.NetworkResultBase
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import java.lang.reflect.Type

class NetworkResultBaseAdapter<T : Any, E : Any>(
    private val successType: Type,
    private val errorConverter: Converter<ResponseBody, E>
) : CallAdapter<T, Call<NetworkResultBase<T, E>>> {

    override fun responseType(): Type = successType

    override fun adapt(call: Call<T>): Call<NetworkResultBase<T, E>> {
        return NetworkCall(call, errorConverter)
    }

}


