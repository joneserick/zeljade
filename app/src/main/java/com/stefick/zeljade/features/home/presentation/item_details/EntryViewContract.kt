package com.stefick.zeljade.features.home.presentation.item_details

import com.stefick.zeljade.core.models.EntryResponse
import com.stefick.zeljade.core.network.base.ErrorResponse

interface EntryViewContract {
    fun showLoader()
    fun hideLoader()
    fun displayEntry(entry: EntryResponse)
    fun displayError(error: ErrorResponse?)
}
