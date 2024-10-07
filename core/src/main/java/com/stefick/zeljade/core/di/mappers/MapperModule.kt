package com.stefick.zeljade.core.di.mappers

import com.stefick.zeljade.core.dto.CategoryDTO
import com.stefick.zeljade.core.dto.CompendiumDTO
import com.stefick.zeljade.core.dto.CompendiumEntryDTO
import com.stefick.zeljade.core.dto.EntryDTO
import com.stefick.zeljade.core.mappers.CategoryMapper
import com.stefick.zeljade.core.mappers.CompendiumEntryMapper
import com.stefick.zeljade.core.mappers.CompendiumMapper
import com.stefick.zeljade.core.mappers.EntryMapper
import com.stefick.zeljade.core.mappers.Mapper
import com.stefick.zeljade.core.models.CategoryModel
import com.stefick.zeljade.core.models.CompendiumEntryModel
import com.stefick.zeljade.core.models.CompendiumModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
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
    ): Mapper<CompendiumEntryDTO, CompendiumEntryModel>

    @Binds
    @Singleton
    abstract fun bindEntryMapper(
        entryMapper: EntryMapper
    ): Mapper<EntryDTO, CompendiumEntryModel>

    @Binds
    @Singleton
    abstract fun bindCategoryMapper(
        categoryMapper: CategoryMapper
    ): Mapper<CategoryDTO, CategoryModel>

    @Binds
    @Singleton
    abstract fun bindCompendiumEntry(
        compendiumEntryModel: CompendiumEntryModel
    ): CompendiumEntryModel

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