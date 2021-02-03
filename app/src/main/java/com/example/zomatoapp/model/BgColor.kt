package com.example.zomatoapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BgColor (
    @SerializedName("type")
    @Expose
    val type:String,
    @SerializedName("tint")
    @Expose
    val tint:String
        )