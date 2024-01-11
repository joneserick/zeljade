package com.stefick.zeljade.core.models

data class CategoriesResponse(
    var creatures: CreaturesResponse? = null,
    var equipment: ArrayList<CategoryResponse>? = null,
    var materials: ArrayList<CategoryResponse>? = null,
    var monsters: ArrayList<CategoryResponse>? = null,
    var treasure: ArrayList<CategoryResponse>? = null,
)
