package com.stefick.zeljade.features.home.presentation.item_details

import com.stefick.zeljade.core.models.EntryResponse

interface EntryViewContract {
    fun showLoader()
    fun hideLoader()
    fun displayEntry(entry: EntryResponse)
    fun displayError(message: String)
}
