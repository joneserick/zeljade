package com.stefick.zeljade.core.network.base

import okhttp3.Headers
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException

sealed class NetworkResultBase<out T : Any?, out E : Any?> {

    data class Success<T : Any>(
        private val headers: Headers? = null,
        val body: T,
        val code: Int?
    ) : NetworkResultBase<T, Nothing>()

    data class ApiError<U : Any>(val body: U, val code: Int, val url: String? = null) :
        NetworkResultBase<Nothing, U>()

    data class NetworkError(val error: IOException, val code: Int) :
        NetworkResultBase<Nothing, Nothing>()

    data class Unknown(
        val error: Throwable?,
        val code: Int,
        val responseBody: ResponseBody?,
        val url: String? = null
    ) : NetworkResultBase<Nothing, Nothing>()

}

fun <T : Any, E : Any> resultFromNetworkResponse(
    response: Response<T>,
    errorConverter: Converter<ResponseBody, E>? = null
): NetworkResultBase<T, E> {
    val body = response.body()
    val code = response.code()
    val error = response.errorBody()

    return if (response.isSuccessful) {
        if (body != null) {
            NetworkResultBase.Success(response.headers(), body, code)
        } else {
            NetworkResultBase.Unknown(
                null,
                code,
                error,
                response.raw().request.url.toString()
            )
        }
    } else {
        val errorBody = when {
            error == null -> null
            error.contentLength() == 0L -> null
            else -> try {
                errorConverter?.convert(error)
            } catch (ex: Exception) {
                null
            }
        }
        if (errorBody != null) {
            NetworkResultBase.ApiError(
                errorBody,
                code,
                response.raw().request.url.toString()
            )
        } else {
            NetworkResultBase.Unknown(
                null,
                code,
                null,
                response.raw().request.url.toString()
            )
        }
    }
}

