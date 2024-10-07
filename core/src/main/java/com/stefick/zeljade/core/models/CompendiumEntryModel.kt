package com.stefick.zeljade.core.models

import android.os.Parcelable
import com.stefick.zeljade.core.enums.CategoryEnum
import kotlinx.parcelize.Parcelize


@Parcelize
data class CompendiumEntryModel(
    val id: Int,
    val name: String,
    val description: String,
    val imageURL: String,
    val category: CategoryEnum,
    val commonLocations: List<String>? = null,
    val cookingEffect: String? = null,
    val heartsRecovered: Int? = null,
    val edible: Boolean? = null,
    val dlc: Boolean? = null,
    val drops: List<String>? = null
):Parcelable