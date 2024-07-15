package com.example.androidfundamentalsapp.retrofitActivity.data.services.request

import com.google.gson.annotations.SerializedName

data class TokenRequest (

    @SerializedName("grant_type")
    val grantType: String,

    @SerializedName("code")
    val code: String,

    @SerializedName("redirect_uri")
    val redirectUri: String,

)