package com.stefick.zeljade.core.network.adapter

import com.stefick.zeljade.core.network.base.Result
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class NetworkCallAdapter(private val resultType: Type) : CallAdapter<Type, Call<Result<Type>>> {

    override fun responseType(): Type = resultType

    override fun adapt(call: Call<Type>): Call<Result<Type>> =
        NetworkCall(call)
}