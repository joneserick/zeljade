package com.stefick.zeljade.core.mappers

import com.stefick.zeljade.core.dto.CategoryDTO
import com.stefick.zeljade.core.enums.CategoryEnum
import com.stefick.zeljade.core.models.CategoryModel
import com.stefick.zeljade.core.models.CompendiumEntryModel
import javax.inject.Inject


class CategoryMapper @Inject constructor() : Mapper<CategoryDTO, CategoryModel> {

    override fun toDomain(dto: CategoryDTO?): CategoryModel? {
        if (dto == null) return null

        return with(dto) {
            CategoryModel(
                entries = data?.map { entry ->
                    CompendiumEntryModel(
                        id = entry.id,
                        name = entry.name,
                        description = entry.description,
                        imageURL = entry.imageURL,
                        commonLocations = entry.commonLocations,
                        cookingEffect = entry.cookingEffect,
                        category = CategoryEnum.fromValueOrDefault(entry.category),
                        heartsRecovered = entry.heartsRecovered,
                        edible = entry.edible,
                        dlc = entry.dlc,
                        drops = entry.drops
                    )
                }
            )
        }
    }

}

