package com.stefick.zeljade.features.home.presentation

import androidx.lifecycle.LifecycleCoroutineScope
import com.stefick.zeljade.core.models.CategoryItemResponse
import com.stefick.zeljade.core.repository.CompendiumRepository
import com.stefick.zeljade.core.repository.Repository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomePresenter(
    private val view: HomeContract.View,
    private val repository: CompendiumRepository,
    private val lifecycleScope: LifecycleCoroutineScope
) {

    fun getDataByCategory(category: String) {
        lifecycleScope.launch {
            repository.requestDataByCategory(category)
                .catch {
                    view.displayError(it)
                    it.printStackTrace()
                }
                .collect { response ->
                    when (response) {
                        is Repository.Result.Success<*> -> view.displayData(response.result as CategoryItemResponse)
                        else -> view.displayError(Throwable())
                    }
                }
        }
    }


}