package com.stefick.zeljade.core.di.datasource

import com.stefick.zeljade.core.api.CompendiumRemoteDataSource
import com.stefick.zeljade.core.api.CompendiumRemoteService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModules {

    @Binds
    @Singleton
    abstract fun bindCompendiumRemoteDataSource(remoteDataSource: CompendiumRemoteService)
            : CompendiumRemoteDataSource
}