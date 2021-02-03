package com.example.zomatoapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseModel2 (
    @SerializedName("location_suggestions")
    @Expose
    val location_suggestions:List<LocationModel>,
    @SerializedName("status")
    @Expose
    val status:String,
    @SerializedName("has_more")
    @Expose
    val has_more:Long,
    @SerializedName("has_total")
    @Expose
    val has_total:Long,
    @SerializedName("user_has_addresses")
    @Expose
    val user_has_addresses:Boolean
        )