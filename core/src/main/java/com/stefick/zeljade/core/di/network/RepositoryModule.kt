package com.stefick.zeljade.core.di.network

import com.stefick.zeljade.core.repository.CompendiumRepository
import com.stefick.zeljade.core.repository.ICompendiumRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(
        repository: CompendiumRepository
    ): ICompendiumRepository

}