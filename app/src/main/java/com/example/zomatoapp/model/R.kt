package com.example.zomatoapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class R (
    @SerializedName("res_id")
    @Expose
    val res_id:Long,
    @SerializedName("is_grocery_store")
    @Expose
    val is_grocery_store:Boolean,
    @SerializedName("has_menu_status")
    @Expose
    val has_menu_status:HasMenuStatus
        )