package com.stefick.zeljade.core.api

import com.stefick.zeljade.core.models.CompendiumResponse
import com.stefick.zeljade.core.models.EntryResponse
import com.stefick.zeljade.core.network.base.ErrorResponse
import com.stefick.zeljade.core.network.base.NetworkFactory
import com.stefick.zeljade.core.network.base.NetworkResultBase

class CompendiumRemoteService() : NetworkFactory<CompendiumAPI>(CompendiumAPI::class.java),
    CompendiumRemoteDataSource {
    override suspend fun requestAllData(): NetworkResultBase<CompendiumResponse, ErrorResponse>? =
        api.requestAllData()

    override suspend fun requestEntryData(entryId: Int): NetworkResultBase<EntryResponse, ErrorResponse>? =
        api.requestEntryData(entryId)

}
