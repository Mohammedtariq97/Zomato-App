package com.example.zomatoapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Title (
    @SerializedName("text")
    @Expose
    val text:String
        )