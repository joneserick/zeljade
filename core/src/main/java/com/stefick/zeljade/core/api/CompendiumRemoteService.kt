package com.stefick.zeljade.core.api

import com.stefick.zeljade.core.dto.CompendiumDTO
import com.stefick.zeljade.core.dto.EntryDTO
import javax.inject.Inject

class CompendiumRemoteService @Inject constructor(private val api: CompendiumAPI) :
    CompendiumRemoteDataSource {

    override suspend fun requestAllData(): CompendiumDTO? =
        api.requestAllData()

    override suspend fun requestEntryData(entryId: String): EntryDTO? =
        api.requestEntryData(entryId)

}
