package com.example.zomatoapp.location

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zomatoapp.api.ApiUserRestClient
import com.example.zomatoapp.model.BaseModel1
import com.example.zomatoapp.model.BaseModel3
import com.example.zomatoapp.network.RetrofitEventListener
import com.example.zomatoapp.restaurants.RestaurantFragment
import retrofit2.Call

class LocationViewModel:ViewModel() {
    val livedataRestaurantSearch = MutableLiveData<BaseModel1>()
    val liveDataNearByResSearch = MutableLiveData<BaseModel3>()

    fun callNearByRestaurantApi(lat: String, lon: String) {
        ApiUserRestClient.instance.getNearByRestaurantDetails(
            lat,
            lon,
            object : RetrofitEventListener {
                override fun onSuccess(call: Call<*>?, response: Any?) {
                    if (response is BaseModel3) {
                        Log.d(RestaurantFragment.TAG, "response= $response")
                        liveDataNearByResSearch.value = response
                    }
                }

                override fun onError(call: Call<*>?, t: Throwable?) {
                    Log.d(RestaurantFragment.TAG, "Error")
                }
            })

    }

}