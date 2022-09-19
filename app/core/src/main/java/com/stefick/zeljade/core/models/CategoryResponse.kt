package com.stefick.zeljade.core.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CategoryResponse(
    var id: Int? = null,
    var name: String? = null,
    var description: String? = null,
    var category: String? = null,
    var image: String? = null,
    var drops: ArrayList<String>? = null,
    var attack: Int? = null,
    var defense: Int? = null,

    var isFoodCreature: Boolean? = null,

    @SerializedName("cooking_effect")
    var cookingEffect: String? = null,

    @SerializedName("hearts_recovered")
    var heartsRecovered: Double? = null,

    @SerializedName("common_locations")
    var commonLocations: ArrayList<String>? = null
) : Serializable
