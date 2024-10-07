package com.stefick.zeljade.core.repository

import com.stefick.zeljade.core.api.ICompendiumRemoteDataSource
import com.stefick.zeljade.core.di.base.IoDispatcher
import com.stefick.zeljade.core.dto.CategoryDTO
import com.stefick.zeljade.core.dto.CompendiumDTO
import com.stefick.zeljade.core.dto.EntryDTO
import com.stefick.zeljade.core.mappers.Mapper
import com.stefick.zeljade.core.models.CategoryModel
import com.stefick.zeljade.core.models.CompendiumEntryModel
import com.stefick.zeljade.core.models.CompendiumModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CompendiumRepository @Inject constructor(
    private val remoteDataSource: ICompendiumRemoteDataSource,
    private val mapper: Mapper<CompendiumDTO, CompendiumModel>,
    private val entryMapper: Mapper<EntryDTO, CompendiumEntryModel>,
    private val categoryMapper: Mapper<CategoryDTO, CategoryModel>,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : ICompendiumRepository {

    override suspend fun requestAllData(): Flow<CompendiumModel?> =
        withContext(dispatcher) {
            flow {
                emit(mapper.toDomain(remoteDataSource.requestAllData()))
            }
        }

    override suspend fun requestEntryData(entryId: CharSequence): Flow<CompendiumEntryModel?> =
        withContext(dispatcher) {
            flow {
                emit(entryMapper.toDomain(remoteDataSource.requestEntryData(entryId.toString())))
            }
        }

    override suspend fun requestCategoryData(categoryName: CharSequence): Flow<CategoryModel?> =
        withContext(dispatcher) {
            flow {
                emit(categoryMapper.toDomain(remoteDataSource.requestCategoryData(categoryName)))
            }
        }

}