package com.example.androidfundamentalsapp.retrofitmvvm.model.remote

import android.util.Base64
import com.example.androidfundamentalsapp.retrofitmvvm.model.remote.services.SpotifyAPIService
import com.example.androidfundamentalsapp.retrofitmvvm.model.remote.services.SpotifyAuthorizationService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WebServiceManager {

    private lateinit var  spotifyAuthorizationService: SpotifyAuthorizationService
    private lateinit var  spotifyAPIService: SpotifyAPIService

    fun getSpotifyAuthorizationService(): SpotifyAuthorizationService {
        if( !this::spotifyAuthorizationService.isInitialized ) {
            val clientCredentials = "${RemoteConstants.CLIENT_ID}:${RemoteConstants.CLIENT_SECRET}"
            val authorizationValue = "Basic " + Base64.encodeToString(clientCredentials.toByteArray(), Base64.NO_WRAP)
            val retrofit  = getRetrofitAuthServer(
                authorizationValue = authorizationValue,
                baseUrl = BASE_URL_SPOTIFY_AUTH_SERVER
            )
            spotifyAuthorizationService = retrofit.create(SpotifyAuthorizationService::class.java)
        }
        return spotifyAuthorizationService
    }

    fun getSpotifyAPIService(): SpotifyAPIService {
        if( !this::spotifyAPIService.isInitialized ) {
            val retrofit  = getRetrofitAuthServer(
                authorizationValue = null,
                baseUrl = BASE_URL_SPOTIFY_API_SERVER
            )
            spotifyAPIService = retrofit.create(SpotifyAPIService::class.java)
        }
        return spotifyAPIService
    }



    private fun getRetrofitAuthServer(
        authorizationValue: String?,
        baseUrl: String
    ):Retrofit{

        val client = getOkhttpClientWithAuthBasic(authorizationValue)

        val retrofit  = Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
    }

    private fun getOkhttpClientWithAuthBasic(authorizationValue: String? ):OkHttpClient{
        val clientBuilder = OkHttpClient().newBuilder()

        if (authorizationValue != null){
            clientBuilder.addInterceptor{ chain ->
                chain.proceed( chain.request().newBuilder()
                    .addHeader(AUTH_HEADER_KEY,authorizationValue)
                    .build()
                )
            }
        }

        return clientBuilder.build()
    }

    private val BASE_URL_SPOTIFY_AUTH_SERVER = "https://accounts.spotify.com/api/"
    private val BASE_URL_SPOTIFY_API_SERVER = "https://api.spotify.com/v1/"
    private val AUTH_HEADER_KEY = "Authorization"
    private val CONTENT_TYPE = "Content-Type"

}