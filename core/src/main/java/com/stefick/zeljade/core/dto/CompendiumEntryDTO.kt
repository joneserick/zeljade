package com.stefick.zeljade.core.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.stefick.zeljade.core.enums.CategoryEnum
import kotlinx.parcelize.Parcelize

@Parcelize
data class CompendiumEntryDTO(
    val category: String,
    @SerializedName("common_locations") val commonLocations: List<String>?,
    val description: String,
    val cookingEffect: String? = null,
    val heartsRecovered: Int? = null,
    val dlc: Boolean? = null,
    val drops: List<String>? = null,
    val id: Int,
    @SerializedName("image") val imageURL: String,
    val name: String
) : Parcelable
