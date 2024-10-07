package com.stefick.zeljade.core.mappers

import com.stefick.zeljade.core.dto.CompendiumDTO
import com.stefick.zeljade.core.models.CompendiumModel
import javax.inject.Inject

class CompendiumMapper @Inject constructor(private val entryMapper: CompendiumEntryMapper) :
    Mapper<CompendiumDTO, CompendiumModel> {
    override fun toDomain(dto: CompendiumDTO?): CompendiumModel? {
        if (dto == null) return null

        return CompendiumModel(
            entries = dto.data.map { entry ->
                entryMapper.toDomain(entry)
            }
        )

    }
}