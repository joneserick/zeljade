package com.stefick.zeljade.domain.usecases

import com.stefick.zeljade.core.models.CompendiumModel
import com.stefick.zeljade.core.repository.ICompendiumRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEntireCompendiumUseCase @Inject constructor(private val repository: ICompendiumRepository) {

    // Do any map or changes on the data before returning to the ViewModel already as a UI formatted
    // Value
    suspend fun getCompendium(): Flow<CompendiumModel?> =
        repository.requestAllData()

}