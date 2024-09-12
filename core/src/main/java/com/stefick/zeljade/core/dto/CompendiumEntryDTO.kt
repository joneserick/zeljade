package com.stefick.zeljade.core.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CompendiumEntryDTO(
    val category: String, // will be an ENUM
    @SerializedName("common_locations") val commonLocations: List<String>?,
    val description: String,
    val dlc: Boolean,
    val drops: List<String>?,
    val id: Int,
    @SerializedName("image") val imageURL: String,
    val name: String
) : Parcelable, ApiDTO
