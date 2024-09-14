package com.stefick.zeljade.core.mappers

import com.stefick.zeljade.core.dto.CompendiumEntryDTO
import com.stefick.zeljade.core.enums.CategoryEnum
import com.stefick.zeljade.core.models.CompendiumEntryModel
import javax.inject.Inject

class CompendiumEntryMapper @Inject constructor() : Mapper<CompendiumEntryDTO, CompendiumEntryModel> {

    override fun toDomain(dto: CompendiumEntryDTO?): CompendiumEntryModel? {
        if (dto == null) return null

        return CompendiumEntryModel(
            category = CategoryEnum.valueOf(dto.category),
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