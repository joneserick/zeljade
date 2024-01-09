package com.stefick.zeljade.core.mocks

import com.stefick.zeljade.core.models.*

object CompendiumMocks {

    @JvmStatic
    fun getMockCompendium(): CompendiumResponse {
        val data = CategoriesResponse(
            getMockCreaturesResponse(),
            arrayListOf(getMockCategoryResponse()),
            arrayListOf(getMockCategoryResponse()),
            arrayListOf(getMockCategoryResponse()),
            arrayListOf(getMockCategoryResponse())
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

    private fun getMockCategoryResponse(): CategoryResponse =
        CategoryResponse(
            id = 1,
            "A mocked item",
            "Just a mocked description",
            "category",
            "https://mockedcompendiumimage.zeljade.ninja/mocked.png",
            drops = arrayListOf(),
            commonLocations = arrayListOf()

        )

}