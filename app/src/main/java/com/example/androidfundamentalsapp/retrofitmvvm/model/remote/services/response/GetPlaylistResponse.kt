package com.example.androidfundamentalsapp.retrofitmvvm.model.remote.services.response

import com.google.gson.annotations.SerializedName

data class GetPlaylistResponse (
    @SerializedName("items")
    val itemList: List<Item>,
)

data class Item (
    @SerializedName("description")
    val description: String,

    @SerializedName("id")
    val playlistId: String,

    @SerializedName("name")
    val playlistName: String,

    @SerializedName("tracks")
    val tracks: Track,

)

data class Track (
    @SerializedName("total")
    val total: Int,
)