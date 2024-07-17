package com.example.androidfundamentalsapp.retrofitmvvm.model.remote.services.response

import com.google.gson.annotations.SerializedName

data class TokenResponse (

    @SerializedName("access_token")
    val accessToken: String,
)