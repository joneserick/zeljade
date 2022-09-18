package com.stefick.zeljade.core.network.base

import com.stefick.zeljade.core.models.CompendiumResponse

data class ErrorResponse(
    val data: ArrayList<CompendiumResponse>,
    val message: String
)