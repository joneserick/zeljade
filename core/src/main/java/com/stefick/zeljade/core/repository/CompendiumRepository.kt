package com.stefick.zeljade.core.repository

import com.stefick.zeljade.core.api.CompendiumRemoteDataSource
import com.stefick.zeljade.core.di.base.IoDispatcher
import com.stefick.zeljade.core.dto.CompendiumDTO
import com.stefick.zeljade.core.mappers.Mapper
import com.stefick.zeljade.core.models.CompendiumModel
import com.stefick.zeljade.core.models.EntryResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CompendiumRepository @Inject constructor(
    private val remoteDataSource: CompendiumRemoteDataSource,
    private val mapper: Mapper<CompendiumDTO, CompendiumModel>,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : ICompendiumRepository {
    override suspend fun requestAllData(): Flow<CompendiumModel?> =
        withContext(dispatcher) {
            flow {
                emit(mapper.toDomain(remoteDataSource.requestAllData()))
            }
        }

    override suspend fun requestEntryData(entryId: Int): Flow<EntryResponse?> =
        withContext(dispatcher) {
            flow {
                emit(remoteDataSource.requestEntryData(entryId))
            }
        }

}