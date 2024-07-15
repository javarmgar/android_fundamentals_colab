package com.example.androidfundamentalsapp.retrofitActivity.data.services.response

import com.google.gson.annotations.SerializedName

data class TokenResponse (

    @SerializedName("access_token")
    val accessToken: String,
)