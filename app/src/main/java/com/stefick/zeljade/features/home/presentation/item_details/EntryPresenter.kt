package com.stefick.zeljade.features.home.presentation.item_details

import com.stefick.zeljade.core.models.EntryResponse
import com.stefick.zeljade.core.network.base.ErrorResponse
import com.stefick.zeljade.core.repository.ICompendiumRepository
import com.stefick.zeljade.core.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class EntryPresenter(
    private val view: EntryViewContract,
    private val repository: ICompendiumRepository,
    private val lifecycleScope: CoroutineScope
) {

    fun getEntry(entryId: Int) {
        lifecycleScope.launch {
            repository.requestEntryData(entryId)
                .onStart { view.showLoader() }
                .collect { result ->
                    when (result) {
                        is Repository.Result.Success<*> -> view.displayEntry((result.result as EntryResponse))
                        is Repository.Result.Failed<*> -> view.displayError((result.result as ErrorResponse))
                        is Repository.Result.Unknown -> {
                            result.responseBody?.let {
                                view.displayError(ErrorResponse(null, it.toString()))
                            }
                        }
                        else -> view.displayError(ErrorResponse(null, null))
                    }
                }
        }
    }
}