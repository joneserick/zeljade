package com.stefick.zeljade.core.mappers

import com.stefick.zeljade.core.dto.CompendiumEntryDTO
import com.stefick.zeljade.core.models.CompendiumEntry
import javax.inject.Inject

class CompendiumEntryMapper @Inject constructor() : Mapper<CompendiumEntryDTO, CompendiumEntry> {

    override fun toDomain(dto: CompendiumEntryDTO?): CompendiumEntry? {
        if (dto == null) return null

        return CompendiumEntry(
            category = dto.category, // will be an ENUM
            commonLocations = dto.commonLocations,
            description = dto.description,
            dlc = dto.dlc,
            drops = dto.drops,
            id = dto.id,
            imageURL = dto.imageURL,
            name = dto.name
        )

    }

}