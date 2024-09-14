package com.stefick.zeljade.core.repository

import com.stefick.zeljade.core.api.CompendiumRemoteDataSource
import com.stefick.zeljade.core.di.base.IoDispatcher
import com.stefick.zeljade.core.dto.CompendiumDTO
import com.stefick.zeljade.core.dto.EntryDTO
import com.stefick.zeljade.core.mappers.Mapper
import com.stefick.zeljade.core.models.CompendiumModel
import com.stefick.zeljade.core.models.CompendiumEntryModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CompendiumRepository @Inject constructor(
    private val remoteDataSource: CompendiumRemoteDataSource,
    private val mapper: Mapper<CompendiumDTO, CompendiumModel>,
    private val entryMapper: Mapper<EntryDTO, CompendiumEntryModel>,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : ICompendiumRepository {

    override suspend fun requestAllData(): Flow<CompendiumModel?> =
        withContext(dispatcher) {
            flow {
                emit(mapper.toDomain(remoteDataSource.requestAllData()))
            }
        }

    override suspend fun requestEntryData(entryId: String): Flow<CompendiumEntryModel?> =
        withContext(dispatcher) {
            flow {
                emit(entryMapper.toDomain(remoteDataSource.requestEntryData(entryId)))
            }
        }

}