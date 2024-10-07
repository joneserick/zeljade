package com.stefick.zeljade.core.api

import com.stefick.zeljade.core.dto.CategoryDTO
import com.stefick.zeljade.core.dto.CompendiumDTO
import com.stefick.zeljade.core.dto.EntryDTO

interface ICompendiumRemoteDataSource {
    suspend fun requestAllData(): CompendiumDTO?
    suspend fun requestEntryData(entryId: String): EntryDTO?
    suspend fun requestCategoryData(categoryName: CharSequence): CategoryDTO?
}