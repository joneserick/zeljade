package com.stefick.zeljade.core.network.adapter

import com.stefick.zeljade.core.network.base.Result
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class NetworkCallAdapterFactory private constructor() : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Call::class.java)
            return null

        val callType = getParameterUpperBound(0, returnType as ParameterizedType)
        if (getRawType(callType) != Result::class.java)
            return null

        val resultType = getParameterUpperBound(0, callType as ParameterizedType)
        return NetworkCallAdapter(resultType)
    }

    companion object {
        fun create(): NetworkCallAdapterFactory = NetworkCallAdapterFactory()
    }

}