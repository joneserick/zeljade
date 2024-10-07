package com.stefick.zeljade.core.mappers

import com.stefick.zeljade.core.dto.CompendiumEntryDTO
import com.stefick.zeljade.core.enums.CategoryEnum
import com.stefick.zeljade.core.models.CompendiumEntryModel
import javax.inject.Inject

class CompendiumEntryMapper @Inject constructor() :
    Mapper<CompendiumEntryDTO, CompendiumEntryModel> {

    override fun toDomain(dto: CompendiumEntryDTO?): CompendiumEntryModel? {
        if (dto == null) return null

        return with(dto) {
            CompendiumEntryModel(
                category = CategoryEnum.fromValueOrDefault(category),
                commonLocations = commonLocations,
                description = description,
                dlc = dlc,
                drops = drops,
                id = id,
                imageURL = imageURL,
                name = name
            )
        }
    }

}