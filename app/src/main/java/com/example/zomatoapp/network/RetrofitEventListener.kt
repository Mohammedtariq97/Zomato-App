package com.example.zomatoapp.network

import retrofit2.Call

interface RetrofitEventListener {
    fun onSuccess(call: Call<*>?, response: Any?)
    fun onError(call: Call<*>?, t: Throwable?)
}