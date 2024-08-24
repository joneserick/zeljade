package com.stefick.zeljade.core.repository

import com.stefick.zeljade.core.models.CompendiumModel
import com.stefick.zeljade.core.models.EntryResponse
import kotlinx.coroutines.flow.Flow

interface ICompendiumRepository {
    suspend fun requestAllData(): Flow<CompendiumModel?>

    suspend fun requestEntryData(entryId: Int): Flow<EntryResponse?>

}
