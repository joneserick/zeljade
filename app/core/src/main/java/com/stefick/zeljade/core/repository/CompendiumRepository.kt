package com.stefick.zeljade.core.repository

import com.stefick.zeljade.core.api.CompendiumRemoteDataSource
import kotlinx.coroutines.flow.Flow

class CompendiumRepository(private val remoteDataSource: CompendiumRemoteDataSource) : Repository(),
    ICompendiumRepository {
    override suspend fun requestAllData(): Flow<Result> =
        flowFromNetworkResponse(remoteDataSource.requestAllData())

    override suspend fun requestEntryData(entryId: Int): Flow<Result> =
        flowFromNetworkResponse(remoteDataSource.requestEntryData(entryId))

}