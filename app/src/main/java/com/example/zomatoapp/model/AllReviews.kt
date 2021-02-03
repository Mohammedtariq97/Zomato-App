package com.example.zomatoapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AllReviews (
    @SerializedName("reviews")
    @Expose
    val reviews:List<Review>
        )