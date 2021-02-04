package com.example.zomatoapp.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zomatoapp.api.ApiUserRestClient
import com.example.zomatoapp.model.BaseModel1
import com.example.zomatoapp.network.RetrofitEventListener
import com.example.zomatoapp.restaurants.RestaurantFragment
import retrofit2.Call

class SearchViewModel:ViewModel() {
    val liveDataSearchRestaurants = MutableLiveData<BaseModel1>()

    fun callSearchRestaurantApi(entityId: String, query: String) {
        ApiUserRestClient.instance.getRestaurantDetails(
            entityId,
            query,
            object : RetrofitEventListener {
                override fun onSuccess(call: Call<*>?, response: Any?) {
                    if (response is BaseModel1) {
                        Log.d(RestaurantFragment.TAG, "response= $response")
                        liveDataSearchRestaurants.value = response
                    }
                }

                override fun onError(call: Call<*>?, t: Throwable?) {
                    Log.d(RestaurantFragment.TAG, "Error")
                }
            })

    }
}