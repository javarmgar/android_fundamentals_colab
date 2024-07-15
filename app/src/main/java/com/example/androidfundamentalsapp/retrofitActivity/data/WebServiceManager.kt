package com.example.androidfundamentalsapp.retrofitActivity.data

import com.example.androidfundamentalsapp.retrofitActivity.data.services.SpotifyAccountService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WebServiceManager {

    private lateinit var  spotifyAccountService: SpotifyAccountService

    fun getSpotifyAccountService(): SpotifyAccountService {
        if( !this::spotifyAccountService.isInitialized ) {
            val retrofit  = Retrofit.Builder()
                .client(HttpClientManager.client)
                .baseUrl(BASE_URL_SPOTIFY_ACCOUNT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            spotifyAccountService = retrofit.create(SpotifyAccountService::class.java)
        }
        return spotifyAccountService
    }

    private val BASE_URL_SPOTIFY_ACCOUNT = "https://accounts.spotify.com/api"
}