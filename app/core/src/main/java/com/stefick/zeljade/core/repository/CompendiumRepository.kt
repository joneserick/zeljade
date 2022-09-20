package com.stefick.zeljade.core.repository

import com.stefick.zeljade.core.api.CompendiumRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class CompendiumRepository(private val remoteDataSource: CompendiumRemoteDataSource) : Repository(),
    ICompendiumRepository {
    override suspend fun requestAllData(): Flow<Result> =
        withContext(Dispatchers.IO) {
            flowFromNetworkResponse(remoteDataSource.requestAllData())
        }

    override suspend fun requestEntryData(entryId: Int): Flow<Result> =
        withContext(Dispatchers.IO) {
            flowFromNetworkResponse(remoteDataSource.requestEntryData(entryId))
        }

}