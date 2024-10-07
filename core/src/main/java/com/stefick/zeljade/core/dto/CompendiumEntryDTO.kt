package com.stefick.zeljade.core.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CompendiumEntryDTO(
    val id: Int,
    val category: String,
    val name: String,
    val description: String,
    @SerializedName("image") val imageURL: String,
    @SerializedName("common_locations") val commonLocations: List<String>?,
    @SerializedName("cooking_effect") val cookingEffect: String? = null,
    @SerializedName("hearts_recovered") val heartsRecovered: Int? = null,
    val edible: Boolean? = null,
    val dlc: Boolean? = null,
    val drops: List<String>? = null,
) : Parcelable
