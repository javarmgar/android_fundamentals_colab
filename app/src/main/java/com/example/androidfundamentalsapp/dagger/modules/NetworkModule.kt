package com.example.androidfundamentalsapp.dagger.modules

import android.util.Base64
import com.example.androidfundamentalsapp.Keys.AUTH_HEADER_KEY
import com.example.androidfundamentalsapp.Keys.BASE_URL_SPOTIFY_AUTH_SERVER
import com.example.androidfundamentalsapp.retrofitmvvm.model.remote.RemoteConstants
import com.example.androidfundamentalsapp.retrofitmvvm.model.remote.services.SpotifyAuthorizationService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideOkHttpClient():OkHttpClient{
        val clientCredentials = "${RemoteConstants.CLIENT_ID}:${RemoteConstants.CLIENT_SECRET}"
        val authorizationValue = "Basic " + Base64.encodeToString(clientCredentials.toByteArray(), Base64.NO_WRAP)
        val clientBuilder = OkHttpClient().newBuilder()
        clientBuilder.addInterceptor{ chain ->
            chain.proceed( chain.request().newBuilder()
                .addHeader(AUTH_HEADER_KEY,authorizationValue)
                .build()
            )
        }
        return clientBuilder.build()
    }

     @Provides
     fun provideRetrofitInstance(client: OkHttpClient):Retrofit{
         val retrofit  = Retrofit.Builder()
             .client(client)
             .baseUrl(BASE_URL_SPOTIFY_AUTH_SERVER)
             .addConverterFactory(GsonConverterFactory.create())
             .build()

         return retrofit
     }

    @Provides
    fun provideSpotifyAuthorizationService(retrofit: Retrofit): SpotifyAuthorizationService{
           return retrofit.create(SpotifyAuthorizationService::class.java)
    }

}