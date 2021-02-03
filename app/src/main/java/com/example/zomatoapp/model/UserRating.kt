package com.example.zomatoapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserRating (
    @SerializedName("aggregate_rating")
    @Expose
    val aggregate_rating:String,
    @SerializedName("rating_text")
    @Expose
    val rating_text:String,
    @SerializedName("rating_color")
    @Expose
    val rating_color:String,
    @SerializedName("rating_obj")
    @Expose
    val rating_obj:RatingObj,
    @SerializedName("votes")
    @Expose
    val votes:Long
        )