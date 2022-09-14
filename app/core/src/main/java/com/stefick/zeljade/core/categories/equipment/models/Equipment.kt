package com.stefick.zeljade.core.categories.equipment.models

import com.stefick.zeljade.core.categories.Category

data class EquipmentResponse(
    val `data`: List<Equipment>
)

data class Equipment(
    val attack: Short,
    val defense: Short,
) : Category()