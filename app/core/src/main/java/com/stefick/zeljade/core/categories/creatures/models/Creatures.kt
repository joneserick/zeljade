package com.stefick.zeljade.core.categories.creatures.models

import com.google.gson.annotations.SerializedName
import com.stefick.zeljade.core.models.CategoryItem

data class CreaturesResponse(
    val food: List<CategoryItem>,
    @SerializedName("non_food")
    val nonFood: List<CategoryItem>
)