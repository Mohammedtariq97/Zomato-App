package com.example.zomatoapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Review (
    @SerializedName("review")
    @Expose
    val review:List<Any?>
        )