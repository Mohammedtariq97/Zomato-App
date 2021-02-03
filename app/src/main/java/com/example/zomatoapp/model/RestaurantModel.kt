package com.example.zomatoapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class RestaurantModel(
    @SerializedName("restaurant")
    @Expose
    val restaurant:Restaurant
)