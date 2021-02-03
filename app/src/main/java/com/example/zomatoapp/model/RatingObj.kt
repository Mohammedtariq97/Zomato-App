package com.example.zomatoapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RatingObj(
    @SerializedName("title")
    @Expose
    val title:Title,
    @SerializedName("bg_color")
    @Expose
    val bg_color:BgColor,
)