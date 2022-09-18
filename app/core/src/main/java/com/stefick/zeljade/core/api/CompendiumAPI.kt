package com.stefick.zeljade.core.api

import com.stefick.zeljade.core.models.CompendiumResponse
import com.stefick.zeljade.core.network.base.ErrorResponse
import com.stefick.zeljade.core.network.base.NetworkResultBase
import retrofit2.http.GET

interface CompendiumAPI {
    @GET("all")
    suspend fun requestAllData(): NetworkResultBase<CompendiumResponse, ErrorResponse>?

}