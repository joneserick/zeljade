package com.stefick.zeljade.core.mocks

import com.stefick.zeljade.core.models.CategoryEnum
import com.stefick.zeljade.core.models.CategoryItem
import com.stefick.zeljade.core.models.CategoryItemResponse

object CategoryMocks {

    @JvmStatic
    fun getMockCategory(category: String): CategoryItemResponse {
        val categoryItem = when (category) {
            CategoryEnum.CREATURES.category -> getCreatureCategoryItem(category)
            CategoryEnum.MONSTERS.category -> getMonsterCategoryItem(category)
            CategoryEnum.MATERIALS.category -> getMaterialsCategoryItem(category)
            CategoryEnum.EQUIPMENT.category -> getEquipmentCategoryItem(category)
            CategoryEnum.TREASURE.category -> getTreasureCategoryItem(category)
            else -> throw NotImplementedError("Should provide a valid category")
        }
        return CategoryItemResponse(listOf(categoryItem))
    }

    private fun getMonsterCategoryItem(category: String): CategoryItem =
        CategoryItem(
            id = 1,
            "A mocked item",
            "Just a mocked description",
            category,
            "https://mockedcompendiumimage.zeljade.ninja/mocked.png",
            drops = listOf(),
            commonLocations = listOf()
        )

    private fun getTreasureCategoryItem(category: String): CategoryItem =
        CategoryItem(
            id = 1,
            "A mocked item",
            "Just a mocked description",
            category,
            "https://mockedcompendiumimage.zeljade.ninja/mocked.png",
            drops = listOf(),
            commonLocations = listOf()
        )

    private fun getMaterialsCategoryItem(category: String): CategoryItem =
        CategoryItem(
            id = 1,
            "A mocked item",
            "Just a mocked description",
            category,
            "https://mockedcompendiumimage.zeljade.ninja/mocked.png",
            commonLocations = listOf(),
            heartsRecovered = 0.0,
            cookingEffect = "Mocked effect"

        )

    private fun getEquipmentCategoryItem(category: String): CategoryItem =
        CategoryItem(
            id = 1,
            "A mocked item",
            "Just a mocked description",
            category,
            "https://mockedcompendiumimage.zeljade.ninja/mocked.png",
            drops = listOf(),
            commonLocations = listOf()
        )

    private fun getCreatureCategoryItem(category: String): CategoryItem =
        CategoryItem(
            nonFood = listOf(getCreatureChildItem(category)),
            food = listOf(getCreatureChildItem(category, true))
        )

    private fun getCreatureChildItem(
        category: String,
        isFoodCreature: Boolean = false
    ): CategoryItem =
        CategoryItem(
            id = 1,
            "A mocked item",
            "Just a mocked description",
            category,
            "https://mockedcompendiumimage.zeljade.ninja/mocked.png",
            drops = listOf(),
            commonLocations = listOf(),
            heartsRecovered = if (isFoodCreature) 100.0 else null,
            cookingEffect = if (isFoodCreature) "Mocked effect" else null

        )
}