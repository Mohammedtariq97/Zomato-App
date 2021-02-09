package com.example.zomatoapp.api

import android.app.Activity
import android.util.Log
import com.example.zomatoapp.model.BaseModel1
import com.example.zomatoapp.model.BaseModel2
import com.example.zomatoapp.model.BaseModel3
import com.example.zomatoapp.network.NetworkClient
import com.example.zomatoapp.network.RetrofitEventListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiUserRestClient : Activity() {
    companion object {
        val instance = ApiUserRestClient()
    }

    var mApiUser: ApiUser? = null

    fun getLocationDetails(query: String, retrofitEventListener: RetrofitEventListener) {
        val retrofit = NetworkClient.retrofitClient
        mApiUser = retrofit.create<ApiUser>(ApiUser::class.java)

        val apiUserCall = mApiUser?.getLocation(query)
        Log.d("ApiUserRestClient", "$apiUserCall")
        apiUserCall?.enqueue(object : Callback<BaseModel2> {

            override fun onResponse(
                call: Call<BaseModel2>?,
                response: Response<BaseModel2>?
            ) {

                if (response?.body() != null) {
                    retrofitEventListener.onSuccess(call, response.body())
                }
            }

            override fun onFailure(call: Call<BaseModel2>?, t: Throwable?) {

                retrofitEventListener.onError(call, t)
            }
        })
    }

    fun getRestaurantDetails(
        entityId: String,
        query: String,
        retrofitEventListener: RetrofitEventListener
    ) {
        val retrofit = NetworkClient.retrofitClient
        mApiUser = retrofit.create<ApiUser>(ApiUser::class.java)

        val apiUserCall = mApiUser?.getRestaurants(entityId, "city", query,"20000")
        Log.d("ApiUserRestClient", "$apiUserCall")
        apiUserCall?.enqueue(object : Callback<BaseModel1> {

            override fun onResponse(
                call: Call<BaseModel1>?,
                response: Response<BaseModel1>?
            ) {

                if (response?.body() != null) {
                    retrofitEventListener.onSuccess(call, response.body())
                }
            }

            override fun onFailure(call: Call<BaseModel1>?, t: Throwable?) {

                retrofitEventListener.onError(call, t)
            }
        })
    }

    fun getNearByRestaurantDetails(
        lat: String,
        lon: String,
        retrofitEventListener: RetrofitEventListener
    ) {
        val retrofit = NetworkClient.retrofitClient
        mApiUser = retrofit.create<ApiUser>(ApiUser::class.java)

        val apiUserCall = mApiUser?.getNearByRestaurantsLocationDetails(lat, lon)
        Log.d("ApiUserRestClient", "$apiUserCall")
        apiUserCall?.enqueue(object : Callback<BaseModel3> {

            override fun onResponse(
                call: Call<BaseModel3>?,
                response: Response<BaseModel3>?
            ) {

                if (response?.body() != null) {
                    retrofitEventListener.onSuccess(call, response.body())
                }
            }

            override fun onFailure(call: Call<BaseModel3>?, t: Throwable?) {

                retrofitEventListener.onError(call, t)
            }
        })
    }

    fun getNearBySearchRestaurantDetails(entityId: String,entityType:String,start:String,
                                         lat: String, lon: String,
        retrofitEventListener: RetrofitEventListener
    ) {
        val retrofit = NetworkClient.retrofitClient
        mApiUser = retrofit.create<ApiUser>(ApiUser::class.java)

        val apiUserCall = mApiUser?.getNearByRestaurants(entityId,entityType,start,"20",
            lat, lon,"20000","real_distance","asc")
        Log.d("ApiUserRestClient", "$apiUserCall")
        apiUserCall?.enqueue(object : Callback<BaseModel1> {

            override fun onResponse(
                call: Call<BaseModel1>?,
                response: Response<BaseModel1>?
            ) {

                if (response?.body() != null) {
                    retrofitEventListener.onSuccess(call, response.body())
                }
            }

            override fun onFailure(call: Call<BaseModel1>?, t: Throwable?) {

                retrofitEventListener.onError(call, t)
            }
        })
    }


}