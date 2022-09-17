package com.stefick.zeljade.core.repository

import com.stefick.zeljade.core.api.CompendiumRemoteDataSource
import kotlinx.coroutines.flow.Flow

class CompendiumRepository(private val remoteDataSource: CompendiumRemoteDataSource) : Repository(),
    ICompendiumRepository {

    override suspend fun requestDataByEntry(entry: String): Flow<Result> =
        flowFromNetworkResponse(remoteDataSource.requestDataByEntry(entry))


    override suspend fun requestDataByCategory(category: String): Flow<Result> =
        flowFromNetworkResponse(remoteDataSource.requestDataByCategory(category))

    override suspend fun requestAllData(): Flow<Result> =
        flowFromNetworkResponse(remoteDataSource.requestAllData())


}