package com.stefick.zeljade.core.network.adapter

import com.stefick.zeljade.core.network.base.Result
import com.stefick.zeljade.core.network.base.flowFromResult
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkCall<T : Any>(private val delegate: Call<T>) : Call<Result<T>> {

    override fun enqueue(callback: Callback<Result<T>>) {
        delegate.enqueue(object : Callback<T> {

            override fun onResponse(call: Call<T>, response: Response<T>) {
                val result = flowFromResult(response)
                callback.onResponse(this@NetworkCall, Response.success(result))
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val result = Result.Exception<T>(t)
                callback.onResponse(this@NetworkCall, Response.success(result))
            }

        })
    }

    override fun clone(): Call<Result<T>> = NetworkCall(delegate.clone())

    override fun execute(): Response<Result<T>> = throw NotImplementedError()

    override fun isExecuted(): Boolean = delegate.isExecuted

    override fun isCanceled(): Boolean = delegate.isCanceled

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()

    override fun cancel() = delegate.cancel()
}