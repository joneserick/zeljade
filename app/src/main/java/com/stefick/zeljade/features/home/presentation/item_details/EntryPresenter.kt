package com.stefick.zeljade.features.home.presentation.item_details

import com.stefick.zeljade.core.repository.ICompendiumRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class EntryPresenter(
    private val view: EntryViewContract,
    private val repository: ICompendiumRepository,
    private val lifecycleScope: CoroutineScope
) {

    fun getEntry(entryId: String) {
        lifecycleScope.launch {
            repository.requestEntryData(entryId)
                .onStart { view.showLoader() }
                .collect { result ->
                    when (result) {
//                        is Repository.Result.Success<*> -> view.displayEntry((result.result as EntryResponse))
//                        is Repository.Result.Failed<*> -> view.displayError(R.string.timeout_error_message)
//                        is Repository.Result.Unknown -> {
//                            view.displayError(R.string.default_error)
//
//                        }
//                        else -> view.displayError(R.string.unknown_error)
                    }
                }
        }
    }
}