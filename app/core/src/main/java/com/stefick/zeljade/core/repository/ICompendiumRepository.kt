package com.stefick.zeljade.core.repository

import kotlinx.coroutines.flow.Flow

interface ICompendiumRepository {
    suspend fun requestDataByEntry(entry: String): Flow<Repository.Result>

    suspend fun requestDataByCategory(category: String): Flow<Repository.Result>

    suspend fun requestAllData(): Flow<Repository.Result>
}
