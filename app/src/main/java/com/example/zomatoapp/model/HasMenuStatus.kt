package com.example.zomatoapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HasMenuStatus (
    @SerializedName("delivery")
    @Expose
    val delivery:String,
    @SerializedName("takeaway")
    @Expose
    val takeaway:Long
        )