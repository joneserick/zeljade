package com.stefick.zeljade.core.api

import com.stefick.zeljade.core.models.CategoryItemResponse
import com.stefick.zeljade.core.network.base.ErrorResponse
import com.stefick.zeljade.core.network.base.NetworkResultBase
import retrofit2.http.GET
import retrofit2.http.Path

interface CompendiumAPI {
    @GET("entry/{entry}")
    suspend fun requestDataByEntry(@Path("entry") category: CharSequence): NetworkResultBase<CategoryItemResponse, ErrorResponse>?
    @GET("category/{category}")
    suspend fun requestDataByCategory(@Path("category") category: CharSequence): NetworkResultBase<CategoryItemResponse, ErrorResponse>?
    @GET("all")
    suspend fun requestAllData(): NetworkResultBase<CategoryItemResponse, ErrorResponse>?

}