package com.stefick.zeljade.core.api

import com.stefick.zeljade.core.dto.CategoryDTO
import com.stefick.zeljade.core.dto.CompendiumDTO
import com.stefick.zeljade.core.dto.EntryDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface CompendiumAPI {
    @GET("all")
    suspend fun requestAllData(): CompendiumDTO?
    @GET("entry/{entry}")
    suspend fun requestEntryData(@Path("entry") entryId: String): EntryDTO?
    @GET("category/{category}")
    suspend fun requestCategoryData(@Path("category") categoryName: String): CategoryDTO?
}