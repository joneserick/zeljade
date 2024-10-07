package com.stefick.zeljade.core.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryDTO(
    val data: List<CompendiumEntryDTO>? = null,
) : Parcelable
