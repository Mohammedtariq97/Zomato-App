package com.example.zomatoapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseModel3(
    @SerializedName("location")
    @Expose
    val location: LocationModel,
    @SerializedName("popularity")
    @Expose
    val popularity: PopularityModel,
    @SerializedName("link")
    @Expose
    val link: String,
    @SerializedName("nearby_restaurants")
    @Expose
    val nearby_restaurants: List<NearByRestaurant>

)

