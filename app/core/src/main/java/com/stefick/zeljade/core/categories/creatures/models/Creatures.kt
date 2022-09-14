package com.stefick.zeljade.core.categories.creatures.models

import com.google.gson.annotations.SerializedName
import com.stefick.zeljade.core.categories.Category
import com.stefick.zeljade.core.categories.Drop

data class CreaturesResponse(
    val food: List<Creature>,
    @SerializedName("non_food")
    val nonFood: List<Creature>
)

data class Creature(
    var cookingEffect: CharSequence? = null,
    var heartsRecovered: Short? = null,
    var drops: List<Drop>? = null
) : Category()