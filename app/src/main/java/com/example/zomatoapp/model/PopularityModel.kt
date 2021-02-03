package com.example.zomatoapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PopularityModel (
    @SerializedName("popularity")
    @Expose
    val popularity:String,
    @SerializedName("nightlife_index")
    @Expose
    val nightlife_index:String,
    @SerializedName("nearby_res")
    @Expose
    val nearby_res:List<String>,
    @SerializedName("top_cuisines")
    @Expose
    val top_cuisines:List<String>,
    @SerializedName("popularity_res")
    @Expose
    val popularity_res:String,
    @SerializedName("nightlife_res")
    @Expose
    val nightlife_res:String,
    @SerializedName("subzone")
    @Expose
    val subzone:String,
    @SerializedName("subzone_id")
    @Expose
    val subzone_id:Long,
    @SerializedName("city")
    @Expose
    val city:String
        )