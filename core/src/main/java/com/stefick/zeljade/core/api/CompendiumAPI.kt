package com.stefick.zeljade.core.api

import com.stefick.zeljade.core.models.CompendiumResponse
import com.stefick.zeljade.core.models.EntryResponse
import com.stefick.zeljade.core.network.base.ErrorResponse
import com.stefick.zeljade.core.network.base.NetworkResultBase
import retrofit2.http.GET
import retrofit2.http.Path

interface CompendiumAPI {
    @GET("all")
    suspend fun requestAllData(): NetworkResultBase<CompendiumResponse, ErrorResponse?>?
    @GET("entry/{entry}")
    suspend fun requestEntryData(@Path("entry") entryId: Int): NetworkResultBase<EntryResponse, ErrorResponse?>?

}