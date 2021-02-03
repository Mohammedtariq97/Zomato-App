package com.example.zomatoapp.model

sealed class Delivery{
    class BoolValue(val value: Boolean) : Delivery()
    class IntegerValue(val value: Long) : Delivery()
}
