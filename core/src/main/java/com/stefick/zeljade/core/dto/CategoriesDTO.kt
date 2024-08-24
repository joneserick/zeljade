package com.stefick.zeljade.core.dto

import android.os.Parcelable
import com.stefick.zeljade.core.models.CategoryResponse
import com.stefick.zeljade.core.models.CreaturesResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoriesDTO(
    var creatures: CreaturesResponse? = null,
    var equipment: ArrayList<CategoryResponse>? = null,
    var materials: ArrayList<CategoryResponse>? = null,
    var monsters: ArrayList<CategoryResponse>? = null,
    var treasure: ArrayList<CategoryResponse>? = null,
):Parcelable
