package com.stefick.zeljade.features.home.presentation

import com.stefick.zeljade.core.models.CategoryItemResponse

interface HomeContract {
    interface View {
        fun displayData(response: CategoryItemResponse)
        fun displayError(throwable: Throwable)
    }
}