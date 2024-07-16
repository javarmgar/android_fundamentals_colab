package com.example.androidfundamentalsapp.retrofitActivity.data

import android.util.Base64
import com.example.androidfundamentalsapp.retrofitActivity.data.services.SpotifyAccountService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WebServiceManager {

    private lateinit var  spotifyAccountService: SpotifyAccountService

    fun getSpotifyAuthService(): SpotifyAccountService {
        if( !this::spotifyAccountService.isInitialized ) {
            val retrofit  = getRetrofitAuthServer()
            spotifyAccountService = retrofit.create(SpotifyAccountService::class.java)
        }
        return spotifyAccountService
    }

    private fun getRetrofitAuthServer():Retrofit{
        val client = getOkhttpClientWithAuthBasic()

        val retrofit  = Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL_SPOTIFY_AUTH_SERVER)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
    }

    private fun getOkhttpClientWithAuthBasic():OkHttpClient{
        val clientCredentials = "${RemoteConstants.CLIENT_ID}:${RemoteConstants.CLIENT_SECRET}"
        val authorizationValue = "Basic " + Base64.encodeToString(clientCredentials.toByteArray(), Base64.NO_WRAP)
        return OkHttpClient().newBuilder()
            .addInterceptor { chain ->
                chain.proceed( chain.request().newBuilder()
                    .addHeader(AUTH_HEADER_KEY,authorizationValue)
                    //.addHeader(CONTENT_TYPE,"application/json")
                    .build()
                )
            }
            .build()
    }

    private val BASE_URL_SPOTIFY_AUTH_SERVER = "https://accounts.spotify.com/api/"
    private val AUTH_HEADER_KEY = "Authorization"
    private val CONTENT_TYPE = "Content-Type"

}