package com.stefick.zeljade.core.categories.treasures.models

data class TreasuresResponse(
    val data: List<Treasure>
)

data class Treasure(
    val drops: List<String>
)