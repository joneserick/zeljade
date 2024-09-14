package com.stefick.zeljade.core.api

import com.stefick.zeljade.core.dto.CategoryDTO
import com.stefick.zeljade.core.dto.CompendiumDTO
import com.stefick.zeljade.core.dto.EntryDTO
import javax.inject.Inject

class CompendiumRemoteService @Inject constructor(private val api: CompendiumAPI) :
    CompendiumRemoteDataSource {

    override suspend fun requestAllData(): CompendiumDTO? =
        api.requestAllData()

    override suspend fun requestEntryData(entryId: String): EntryDTO? = //TODO change to CharSequence
        api.requestEntryData(entryId)

    override suspend fun requestCategoryData(categoryName: CharSequence): CategoryDTO? =
        api.requestCategoryData(categoryName.toString())
}
