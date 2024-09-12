package com.stefick.zeljade.core.api

import com.stefick.zeljade.core.dto.CompendiumDTO
import com.stefick.zeljade.core.models.EntryResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CompendiumAPI {
    @GET("all")
    suspend fun requestAllData(): CompendiumDTO?
    @GET("entry/{entry}")
    suspend fun requestEntryData(@Path("entry") entryId: Int): EntryResponse?

}