package com.stefick.zeljade.core.models


data class CompendiumEntry(
    val category: String, // will be an ENUM
    val commonLocations: List<String>?,
    val description: String,
    val dlc: Boolean,
    val drops: List<String>?,
    val id: Int,
    val imageURL: String,
    val name: String
)