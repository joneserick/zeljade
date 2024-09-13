package com.stefick.zeljade.features.home.presentation.item_details

import androidx.annotation.StringRes
import com.stefick.zeljade.core.dto.EntryDTO

interface EntryViewContract {
    fun showLoader()
    fun hideLoader()
    fun displayEntry(entry: EntryDTO)
    fun displayError(@StringRes message: Int)
}
