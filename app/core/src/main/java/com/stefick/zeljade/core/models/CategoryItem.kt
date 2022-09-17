package com.stefick.zeljade.core.categories

import com.google.gson.annotations.SerializedName

abstract class CategoryItem {
    protected var id: Short? = null
    protected var name: CharSequence? = null
    protected var description: CharSequence? = null
    protected var category: CharSequence? = null
    protected var image: CharSequence? = null
    protected var drops: List<Drop>? = null

    @SerializedName("cooking_effect")
    protected var cookingEffect: CharSequence? = null

    @SerializedName("hearts_recovered")
    protected var heartsRecovered: Short? = null

    @SerializedName("common_locations")
    protected var commonLocations: List<CharSequence>? = null
}


