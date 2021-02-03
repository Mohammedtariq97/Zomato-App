package com.example.zomatoapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LocationModel (
    @SerializedName("entity_type")
    @Expose
    val entity_type:String,
    @SerializedName("entity_id")
    @Expose
    val entity_id:Long,
    @SerializedName("title")
    @Expose
    val title:String,
    @SerializedName("latitude")
    @Expose
    val latitude:Double,
    @SerializedName("longitude")
    @Expose
    val longitude:Double,
    @SerializedName("city_id")
    @Expose
    val city_id:Long,
    @SerializedName("city_name")
    @Expose
    val city_name:String,
    @SerializedName("country_id")
    @Expose
    val country_id:Long,
    @SerializedName("country_name")
    @Expose
    val country_name:String
        )