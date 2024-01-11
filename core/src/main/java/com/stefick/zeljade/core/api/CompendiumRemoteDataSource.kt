package com.stefick.zeljade.core.api

import com.stefick.zeljade.core.models.CompendiumResponse
import com.stefick.zeljade.core.models.EntryResponse
import com.stefick.zeljade.core.network.base.ErrorResponse
import com.stefick.zeljade.core.network.base.NetworkResultBase

interface CompendiumRemoteDataSource {
    suspend fun requestAllData(): NetworkResultBase<CompendiumResponse, ErrorResponse?>?
   suspend fun requestEntryData(entryId: Int): NetworkResultBase<EntryResponse, ErrorResponse?>?
}