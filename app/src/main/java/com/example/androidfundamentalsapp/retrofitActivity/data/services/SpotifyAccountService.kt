package com.example.androidfundamentalsapp.retrofitActivity.data.services

import com.example.androidfundamentalsapp.retrofitActivity.data.services.response.TokenResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SpotifyAccountService {

//    @FormUrlEncoded
//    @POST("token")
//    fun getAccessToken(@Body tokenRequest: TokenRequest): Call<TokenResponse>

    @FormUrlEncoded
    @POST("token")
    fun getAccessToken(
        @Field("grant_type") grantType: String,
        @Field("code") code: String,
        @Field("redirect_uri") redirectUri: String,
    ): Call<TokenResponse>

}
