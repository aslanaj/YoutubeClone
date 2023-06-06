package com.simbadev.youtubeclone.remote

import com.simbadev.youtubeclone.model.PlayLists
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("playlists")
    fun getPlayList(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("key") apiKey: String
    ): Call<PlayLists>

}