package com.example.zomatoapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Restaurant (
    @SerializedName("R")
    @Expose
    val r:R,
    @SerializedName("apikey")
    @Expose
    val apikey:String,
    @SerializedName("id")
    @Expose
    val id:String,
    @SerializedName("name")
    @Expose
    val name:String,
    @SerializedName("url")
    @Expose
    val url:String,
    @SerializedName("location")
    @Expose
    val location:Location,
    @SerializedName("switch_to_order_menu")
    @Expose
    val switch_to_order_menu:Long,
    @SerializedName("cuisines")
    @Expose
    val cuisines:String,
    @SerializedName("timings")
    @Expose
    val timings:String,
    @SerializedName("average_cost_for_two")
    @Expose
    val average_cost_for_two:Long,
    @SerializedName("price_range")
    @Expose
    val price_range:Long,
    @SerializedName("currency")
    @Expose
    val currency:String,
    @SerializedName("highlights")
    @Expose
    val highlights:List<String>,
    @SerializedName("offers")
    @Expose
    val offers:List<Any?>,
    @SerializedName("opentable_support")
    @Expose
    val opentable_support:Long,
    @SerializedName("is_zomato_book_res")
    @Expose
    val is_zomato_book_res:Long,
    @SerializedName("mezzo_provider")
    @Expose
    val mezzo_provider:String,
    @SerializedName("is_book_form_web_view")
    @Expose
    val is_book_form_web_view:Long,
    @SerializedName("book_form_web_view_url")
    @Expose
    val book_form_web_view_url:String,
    @SerializedName("book_again_url")
    @Expose
    val book_again_url:String,
    @SerializedName("thumb")
    @Expose
    val thumb:String,
    @SerializedName("user_rating")
    @Expose
    val user_rating:UserRating,
    @SerializedName("all_reviews_count")
    @Expose
    val all_reviews_count:Long,
    @SerializedName("photos_url")
    @Expose
    val photos_url:String,
    @SerializedName("photo_count")
    @Expose
    val photo_count:Long,
    @SerializedName("menu_url")
    @Expose
    val menu_url:String,
    @SerializedName("featured_image")
    @Expose
    val featured_image:String,
    @SerializedName("medio_provider")
    @Expose
    val medio_provider:Boolean,
    @SerializedName("has_online_delivery")
    @Expose
    val has_online_delivery:Long,
    @SerializedName("is_delivering_now")
    @Expose
    val is_delivering_now:Long,
    @SerializedName("store_type")
    @Expose
    val store_type:String,
    @SerializedName("include_bogo_offers")
    @Expose
    val include_bogo_offers:Boolean,
    @SerializedName("deeplink")
    @Expose
    val deeplink:String,
    @SerializedName("order_url")
    @Expose
    val order_url:String?=null,
    @SerializedName("order_deeplink")
    @Expose
    val order_deeplink:String?=null,
    @SerializedName("is_table_reservation_supported")
    @Expose
    val is_table_reservation_supported:Long,
    @SerializedName("has_table_booking")
    @Expose
    val has_table_booking:Long,
    @SerializedName("events_url")
    @Expose
    val events_url:String,
    @SerializedName("phone_numbers")
    @Expose
    val phone_numbers:String,
    @SerializedName("all_reviews")
    @Expose
    val all_reviews:AllReviews,
    @SerializedName("establishment")
    @Expose
    val establishment:List<String>,
    @SerializedName("establishment_types")
    @Expose
    val establishment_types:List<Any?>
        )