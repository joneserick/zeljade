package com.stefick.zeljade.core.di.mappers

import com.stefick.zeljade.core.dto.CompendiumDTO
import com.stefick.zeljade.core.dto.CompendiumEntryDTO
import com.stefick.zeljade.core.mappers.CompendiumEntryMapper
import com.stefick.zeljade.core.mappers.CompendiumMapper
import com.stefick.zeljade.core.mappers.Mapper
import com.stefick.zeljade.core.models.CompendiumEntry
import com.stefick.zeljade.core.models.CompendiumModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    @Singleton
    abstract fun bindCompendiumMapper(
        compendiumMapper: CompendiumMapper
    ): Mapper<CompendiumDTO, CompendiumModel>

    @Binds
    @Singleton
    abstract fun bindCompendiumEntryMapper(
        compendiumEntryMapper: CompendiumEntryMapper
    ): Mapper<CompendiumEntryDTO, CompendiumEntry>


    @Binds
    @Singleton
    abstract fun bindCompendiumEntry(
        compendiumEntry: CompendiumEntry
    ): CompendiumEntry

    @Binds
    @Singleton
    abstract fun bindCompendiumDTO(
        compendiumDTO: CompendiumDTO
    ): CompendiumDTO

    @Binds
    @Singleton
    abstract fun bindCompendiumModel(
        compendiumModel: CompendiumModel
    ): CompendiumModel

}