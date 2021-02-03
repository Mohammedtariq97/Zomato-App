package com.example.zomatoapp.restaurants

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zomatoapp.api.ApiUserRestClient
import com.example.zomatoapp.model.BaseModel1
import com.example.zomatoapp.model.BaseModel2
import com.example.zomatoapp.model.BaseModel3
import com.example.zomatoapp.network.RetrofitEventListener
import retrofit2.Call

class RestaurantFragmentViewModel : ViewModel() {
    val livedataRestaurantSearch = MutableLiveData<BaseModel1>()
    val liveDataLocationSearch = MutableLiveData<BaseModel2>()
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

    fun callSearchLocationApi(query: String) {
        ApiUserRestClient.instance.getLocationDetails(query, object : RetrofitEventListener {
            override fun onSuccess(call: Call<*>?, response: Any?) {
                if (response is BaseModel2) {
                    Log.d(RestaurantFragment.TAG, "response= $response")
                    liveDataLocationSearch.value = response
                }
            }

            override fun onError(call: Call<*>?, t: Throwable?) {
                Log.d(RestaurantFragment.TAG, "Error")
            }
        })

    }

    fun callSearchRestaurantApi(entityId: String, query: String) {
        ApiUserRestClient.instance.getRestaurantDetails(
            entityId,
            query,
            object : RetrofitEventListener {
                override fun onSuccess(call: Call<*>?, response: Any?) {
                    if (response is BaseModel1) {
                        Log.d(RestaurantFragment.TAG, "response= $response")
                        livedataRestaurantSearch.value = response
                    }
                }

                override fun onError(call: Call<*>?, t: Throwable?) {
                    Log.d(RestaurantFragment.TAG, "Error")
                }
            })

    }
}