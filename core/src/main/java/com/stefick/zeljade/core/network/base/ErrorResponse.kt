package com.stefick.zeljade.core.network.base

import com.stefick.zeljade.core.dto.CompendiumDTO

data class ErrorResponse(
    val data: ArrayList<CompendiumDTO>?,
    val message: String?
)