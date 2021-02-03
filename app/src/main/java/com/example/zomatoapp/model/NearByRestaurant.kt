package com.example.zomatoapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NearByRestaurant (
    @SerializedName("restaurant")
    @Expose
    val restaurant:Restaurant1
        )