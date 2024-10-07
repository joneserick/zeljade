package com.stefick.zeljade.core.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class EntryDTO(
    var data: CompendiumEntryDTO? = null
) : Parcelable