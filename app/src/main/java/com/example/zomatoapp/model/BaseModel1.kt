package com.example.zomatoapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseModel1 (
    @SerializedName("results_found")
    @Expose
    val results_found:Int,
    @SerializedName("results_start")
    @Expose
    val results_start:Int,
    @SerializedName("results_shown")
    @Expose
    val results_shown:Int,
    @SerializedName("restaurants")
    @Expose
    val restaurants:List<RestaurantModel>
    )