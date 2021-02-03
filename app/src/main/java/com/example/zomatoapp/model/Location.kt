package com.example.zomatoapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Location (
    @SerializedName("address")
    @Expose
    val address:String,
    @SerializedName("locality")
    @Expose
    val locality:String,
    @SerializedName("city")
    @Expose
    val city:String,
    @SerializedName("city_id")
    @Expose
    val city_id:Long,
    @SerializedName("latitude")
    @Expose
    val latitude:String,
    @SerializedName("longitude")
    @Expose
    val longitude:String,
    @SerializedName("zipcode")
    @Expose
    val zipcode:String,
    @SerializedName("country_id")
    @Expose
    val country_id:Long,
    @SerializedName("locality_verbose")
    @Expose
    val locality_verbose:String,
        )