package com.stefick.zeljade.core.api

import com.stefick.zeljade.core.dto.CompendiumDTO
import com.stefick.zeljade.core.models.EntryResponse
import com.stefick.zeljade.core.network.base.ErrorResponse
import com.stefick.zeljade.core.network.base.NetworkResultBase
import kotlinx.coroutines.flow.Flow

interface CompendiumRemoteDataSource {
    suspend fun requestAllData(): CompendiumDTO?
    suspend fun requestEntryData(entryId: Int): EntryResponse?
}