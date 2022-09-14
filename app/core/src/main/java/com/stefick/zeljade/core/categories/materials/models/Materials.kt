package com.stefick.zeljade.core.categories.materials.models

import com.stefick.zeljade.core.categories.Category

data class MaterialsResponse(
    val data: List<Materials>
)

data class Materials(
    var cookingEffect: CharSequence? = null,
    var heartsRecovered: Short? = null
) : Category()
