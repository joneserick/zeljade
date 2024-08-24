package com.stefick.zeljade.core.api

import com.stefick.zeljade.core.dto.CompendiumDTO
import com.stefick.zeljade.core.models.EntryResponse
import com.stefick.zeljade.core.network.base.ErrorResponse
import com.stefick.zeljade.core.network.base.NetworkFactory
import com.stefick.zeljade.core.network.base.NetworkResultBase
import javax.inject.Inject

class CompendiumRemoteService @Inject constructor(private val api: CompendiumAPI) :
    CompendiumRemoteDataSource {

    override suspend fun requestAllData(): CompendiumDTO? =
        api.requestAllData()

    override suspend fun requestEntryData(entryId: Int): EntryResponse? =
        api.requestEntryData(entryId)

}
