package com.stefick.zeljade.core.models

import com.google.gson.annotations.SerializedName

data class CreaturesResponse(
    var food: ArrayList<CategoryResponse>,
    @SerializedName("non_food")
    var nonFood: ArrayList<CategoryResponse>
)


