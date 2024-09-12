package com.stefick.zeljade.core.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CompendiumDTO(
    var data: List<CompendiumEntryDTO>
):Parcelable
