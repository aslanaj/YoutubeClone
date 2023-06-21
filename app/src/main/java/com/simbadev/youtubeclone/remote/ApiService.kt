package com.simbadev.youtubeclone.remote

import com.simbadev.youtubeclone.remote.model.PlayLists
import com.simbadev.youtubeclone.remote.model.PlaylistItems
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("playlists")
    fun getPlayList(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("key") key: String,
        @Query("maxResults") maxResults: Int = 20
    ): Call<PlayLists>


    @GET("playlistItems")
    fun getPlayListItems(
        @Query("part") part: String,
        @Query("key") key: String,
        @Query("playlistId") playlistId: String,
        @Query("maxResults") maxResults: Int = 10
    ): Call<PlaylistItems>
}