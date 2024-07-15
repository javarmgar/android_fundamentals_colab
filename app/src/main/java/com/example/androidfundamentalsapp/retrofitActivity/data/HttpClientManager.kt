package com.example.androidfundamentalsapp.retrofitActivity.data

import okhttp3.OkHttpClient

object HttpClientManager {
    var client = OkHttpClient()
        .newBuilder()
        //.addInterceptor() // we can add more configurations later
        .build()
}