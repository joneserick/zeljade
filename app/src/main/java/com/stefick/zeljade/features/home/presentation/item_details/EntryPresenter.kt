package com.stefick.zeljade.features.home.presentation.item_details

import androidx.lifecycle.LifecycleCoroutineScope
import com.stefick.zeljade.core.models.EntryResponse
import com.stefick.zeljade.core.network.base.ErrorResponse
import com.stefick.zeljade.core.repository.ICompendiumRepository
import com.stefick.zeljade.core.repository.Repository
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class EntryPresenter(
    private val view: EntryViewContract,
    private val repository: ICompendiumRepository,
    private val lifecycleScope: LifecycleCoroutineScope
) {

    fun getEntry(entryId: Int) {
        lifecycleScope.launch {
            repository.requestEntryData(entryId)
                .onStart { view.showLoader() }
                .collect { result ->
                    when (result) {
                        is Repository.Result.Success<*> -> view.displayEntry((result.result as EntryResponse))
                        is Repository.Result.Failed<*> -> view.displayError((result.result as ErrorResponse).message)
                        is Repository.Result.Unknown -> {
                            view.displayError("WIP: should improve error response: ${result.url}")
                        }
                        else -> view.displayError("WIP: should improve error response:")
                    }
                }
        }
    }
}