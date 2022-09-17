package com.stefick.zeljade.core.models

import com.google.gson.annotations.SerializedName

data class CategoryItem(
    var id: Int? = null,
    var name: String? = null,
    var description: String? = null,
    var category: String? = null,
    var image: String? = null,
    var drops: List<String>? = null,
    var food: List<CategoryItem>? = null,
    var attack: Int? = null,
    var defense: Int? = null,

    @SerializedName("non_food")
    var nonFood: List<CategoryItem>? = null,

    @SerializedName("cooking_effect")
    var cookingEffect: String? = null,

    @SerializedName("hearts_recovered")
    var heartsRecovered: Double? = null,

    @SerializedName("common_locations")
    var commonLocations: List<String>? = null
)


