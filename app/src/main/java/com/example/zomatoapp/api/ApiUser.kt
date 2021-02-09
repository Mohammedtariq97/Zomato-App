package com.example.zomatoapp.api

import com.example.zomatoapp.model.BaseModel1
import com.example.zomatoapp.model.BaseModel2
import com.example.zomatoapp.model.BaseModel3
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiUser {
    @Headers("user-key: ddbd4d09f67bd39cc8d20b84e6834f1f")
    @GET("api/v2.1/locations")
    fun getLocation(
        @Query("query") q: String
    ): Call<BaseModel2>

    @Headers("user-key: ddbd4d09f67bd39cc8d20b84e6834f1f")
    @GET("api/v2.1/search")
    fun getRestaurants(
        @Query("entity_id") entityId: String,
        @Query("entity_type") entityType: String,
        @Query("q") q: String,
        @Query("radius") radius: String
    ): Call<BaseModel1>

    @Headers("user-key: ddbd4d09f67bd39cc8d20b84e6834f1f")
    @GET("api/v2.1/geocode")
    fun getNearByRestaurantsLocationDetails(
        @Query("lat") lat: String,
        @Query("lon") lon: String
    ): Call<BaseModel3>

    @Headers("user-key: ddbd4d09f67bd39cc8d20b84e6834f1f")
    @GET("api/v2.1/search")
    fun getNearByRestaurants(
        @Query("entity_id") entityId: String,
        @Query("entity_type") entityType: String,
        @Query("start") start: String,
        @Query("count") count: String,
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("radius") radius: String,
        @Query("sort") sort: String,
        @Query("order") order: String
    ): Call<BaseModel1>


}

//https://developers.zomato.com/api/v2.1/search?entity_id=11332&entity_type=city&start=0&count=20&lat=10.7905&lon=78.7047&radius=20000&sort=real_distance&order=asc
