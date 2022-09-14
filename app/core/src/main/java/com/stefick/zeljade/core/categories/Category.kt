package com.stefick.zeljade.core.categories

import com.google.gson.annotations.SerializedName

abstract class Category {
    protected var id: Short? = null
    protected var name: CharSequence? = null
    protected var description: CharSequence? = null
    protected var category: CharSequence? = null
    protected var image: CharSequence? = null

    @SerializedName("common_locations")
    protected var commonLocations: List<CharSequence>? = null
}


