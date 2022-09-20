package com.stefick.zeljade.presentation

import com.stefick.zeljade.core.models.CategoriesResponse
import com.stefick.zeljade.core.models.CategoryResponse
import com.stefick.zeljade.core.models.CompendiumResponse
import com.stefick.zeljade.core.models.CreaturesResponse

object Mocks {


    @JvmStatic
    fun getMockCompendium(): CompendiumResponse {
        val data = CategoriesResponse(
            creatures = getMockCreaturesResponse(),
            equipment = arrayListOf(getMockCategoryResponse("equipment")),
            materials = arrayListOf(getMockCategoryResponse("materials")),
            monsters = arrayListOf(getMockCategoryResponse("monsters")),
            treasure = arrayListOf(getMockCategoryResponse("treasure"))
        )
        return CompendiumResponse(data)
    }

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