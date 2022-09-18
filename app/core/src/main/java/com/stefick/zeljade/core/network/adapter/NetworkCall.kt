package com.stefick.zeljade.core.network.adapter

import com.stefick.zeljade.core.network.base.NetworkResultBase
import com.stefick.zeljade.core.network.base.resultFromNetworkResponse
import okhttp3.Request
import okhttp3.ResponseBody
import okio.IOException
import okio.Timeout
import retrofit2.*
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class NetworkCall<T : Any, E : Any>(
    private val delegate: Call<T>,
    private val errorConverter: Converter<ResponseBody, E>
) : Call<NetworkResultBase<T, E>> {

    override fun enqueue(callback: Callback<NetworkResultBase<T, E>>) {
        delegate.enqueue(object : Callback<T> {

            override fun onResponse(call: Call<T>, response: Response<T>) {
                val result = resultFromNetworkResponse(response, errorConverter)
                callback.onResponse(this@NetworkCall, Response.success(result))
            }

            override fun onFailure(call: Call<T>, throwable: Throwable) {
                throwable.printStackTrace()
                val networkResponse = when (throwable) {
                    is HttpException -> NetworkResultBase.NetworkError(throwable, 500)
                    is TimeoutException -> NetworkResultBase.NetworkError(throwable, 408)
                    is IOException -> NetworkResultBase.NetworkError(throwable, -1)
                    else -> NetworkResultBase.Unknown(throwable, -1, null)
                }
                callback.onResponse(this@NetworkCall, Response.success(networkResponse))
            }

        })
    }

    override fun clone(): Call<NetworkResultBase<T, E>> =
        NetworkCall(delegate.clone(), errorConverter)

    override fun execute(): Response<NetworkResultBase<T, E>> = throw NotImplementedError()

    override fun isExecuted(): Boolean = delegate.isExecuted

    override fun isCanceled(): Boolean = delegate.isCanceled

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = Timeout().timeout(360, TimeUnit.SECONDS)

    override fun cancel() = delegate.cancel()
}