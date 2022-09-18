package com.stefick.zeljade.core.repository

import kotlinx.coroutines.flow.Flow

interface ICompendiumRepository {
    suspend fun requestAllData(): Flow<Repository.Result>
}
