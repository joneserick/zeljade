package com.stefick.zeljade.core.categories.monsters.models

import com.stefick.zeljade.core.categories.Category
import com.stefick.zeljade.core.categories.Drop

internal data class MonsterResponse(
    val `data`: List<Monster>
)

data class Monster(
    val drops: List<Drop>,
) : Category()



