package com.stefick.zeljade.core.repository

import com.stefick.zeljade.core.dto.CategoryDTO
import com.stefick.zeljade.core.models.CategoryModel
import com.stefick.zeljade.core.models.CompendiumEntryModel
import com.stefick.zeljade.core.models.CompendiumModel
import kotlinx.coroutines.flow.Flow

interface ICompendiumRepository {
    suspend fun requestAllData(): Flow<CompendiumModel?>

    suspend fun requestEntryData(entryId: CharSequence): Flow<CompendiumEntryModel?>

    suspend fun requestCategoryData(categoryName: CharSequence): Flow<CategoryModel?>

}
