package com.example.androidfundamentalsapp.retrofitmvvm.model.remote.services

import com.example.androidfundamentalsapp.retrofitmvvm.model.remote.services.response.GetPlaylistResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface SpotifyAPIService {
    @GET("users/{userId}/playlists")
    fun getUserPlaylists(
        @Path("userId") userId: String,
        @Header("Authorization") authorization: String,
    ):Call<GetPlaylistResponse>

}