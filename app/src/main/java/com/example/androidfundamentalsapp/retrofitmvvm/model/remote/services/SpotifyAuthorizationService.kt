package com.example.androidfundamentalsapp.retrofitmvvm.model.remote.services

import com.example.androidfundamentalsapp.retrofitmvvm.model.remote.services.response.TokenResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SpotifyAuthorizationService {
    @FormUrlEncoded
    @POST("token")
    fun getAccessToken(
        @Field("grant_type") grantType: String,
        @Field("code") code: String,
        @Field("redirect_uri") redirectUri: String,
    ): Call<TokenResponse>

}
