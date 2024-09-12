package com.stefick.zeljade.presentation

import com.stefick.zeljade.core.dto.CategoriesDTO
import com.stefick.zeljade.core.dto.CompendiumDTO
import com.stefick.zeljade.core.models.*

object Mocks {


    @JvmStatic
    fun getMockCompendium(): CompendiumDTO {
        val data = CategoriesDTO(
            creatures = getMockCreaturesResponse(),
            equipment = arrayListOf(getMockCategoryResponse("equipment")),
            materials = arrayListOf(getMockCategoryResponse("materials")),
            monsters = arrayListOf(getMockCategoryResponse("monsters")),
            treasure = arrayListOf(getMockCategoryResponse("treasure"))
        )
        return CompendiumDTO(data)
    }

    @JvmStatic
    fun getMockEntryResponse(): EntryResponse =
        EntryResponse(getMockCategoryResponse("monsters")) // any category just for test purpose

    private fun getMockCreaturesResponse(): CreaturesResponse =
        CreaturesResponse(
            arrayListOf( //food
                CategoryResponse(
                    id = 1,
                    "A mocked item",
                    "Just a mocked description",
                    "creatures",
                    "https://mockedcompendiumimage.zeljade.ninja/mocked.png",
                    drops = arrayListOf(),
                    commonLocations = arrayListOf()
                )
            ),
            arrayListOf( //nonFood
                CategoryResponse(
                    id = 1,
                    "A mocked item",
                    "Just a mocked description",
                    "creatures",
                    "https://mockedcompendiumimage.zeljade.ninja/mocked.png",
                    drops = arrayListOf(),
                    commonLocations = arrayListOf()
                )
            )
        )

    private fun getMockCategoryResponse(category: String): CategoryResponse =
        CategoryResponse(
            id = 1,
            "A mocked item",
            "Just a mocked description",
            category,
            "https://mockedcompendiumimage.zeljade.ninja/mocked.png",
            drops = arrayListOf(),
            commonLocations = arrayListOf()
        )

}