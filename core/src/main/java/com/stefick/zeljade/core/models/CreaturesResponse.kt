package com.stefick.zeljade.core.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CreaturesResponse(
    var food: ArrayList<CategoryResponse>,
    @SerializedName("non_food")
    var nonFood: ArrayList<CategoryResponse>
):Parcelable


