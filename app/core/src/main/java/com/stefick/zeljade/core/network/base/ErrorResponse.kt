package com.stefick.zeljade.core.network.base

import com.stefick.zeljade.core.models.CategoryItemResponse

data class ErrorResponse(
    val data: List<CategoryItemResponse>,
    val message: String
)