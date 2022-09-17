package com.stefick.zeljade.core.network.adapter

import com.stefick.zeljade.core.network.base.NetworkResultBase
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

        check(returnType is ParameterizedType) {
            "Return type must be parametrized as Call<NetworkResultBase<T, E>>"
        }

        val responseType = getParameterUpperBound(0, returnType)
        if (getRawType(responseType) != NetworkResultBase::class.java)
            return null

        check(responseType is ParameterizedType) {
            "Return type must be parametrized ad NetworkResultBase<T, E>"
        }

        val successResponseType = getParameterUpperBound(0, responseType)
        val errorResponseType = getParameterUpperBound(1, responseType)

        val errorConverter =
            retrofit.nextResponseBodyConverter<Any>(null, errorResponseType, annotations)

        return NetworkResultBaseAdapter<Any, Any>(successResponseType, errorConverter)
    }

    companion object {
        fun create(): NetworkCallAdapterFactory = NetworkCallAdapterFactory()
    }

}