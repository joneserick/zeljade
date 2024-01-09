package com.stefick.zeljade.core.repository

import com.stefick.zeljade.core.network.base.NetworkResultBase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody

abstract class Repository {

    sealed class Result {

        data class Success<T>(val result: T, val code: Int?) : Result()

        data class Failed<T>(val result: T, val code: Int?, val url: String? = null) : Result()

        data class Error(val code: Int?, val response: NetworkResultBase<Any?, Any?>?) : Result()

        data class Unknown(val code: Int?, val url: String? = null, val responseBody: ResponseBody?) : Result()
    }

    protected fun <T, E> flowFromNetworkResponse(
        request: NetworkResultBase<T, E>?
    ): Flow<Result> = flow {

        when (request) {
            is NetworkResultBase.Success<*> -> {
                this.emit(Result.Success(request.body, request.code))
            }
            is NetworkResultBase.Unknown -> {
                this.emit(Result.Unknown(request.code, request.url, request.responseBody))
            }
            is NetworkResultBase.ApiError<*> -> {
                this.emit(Result.Failed(request.body, request.code, request.url))
            }
            is NetworkResultBase.NetworkError -> {
                this.emit(Result.Error(request.code, request))
            }
            else -> {
                this.emit(Result.Error(-1, request))
            }
        }
    }
}
