package com.stefick.zeljade.core.mappers

import com.stefick.zeljade.core.dto.EntryDTO
import com.stefick.zeljade.core.enums.CategoryEnum
import com.stefick.zeljade.core.models.CompendiumEntryModel
import javax.inject.Inject

class EntryMapper @Inject constructor() : Mapper<EntryDTO, CompendiumEntryModel> {
    override fun toDomain(dto: EntryDTO?): CompendiumEntryModel? {
        if (dto == null) return null

        return dto.data?.run {
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