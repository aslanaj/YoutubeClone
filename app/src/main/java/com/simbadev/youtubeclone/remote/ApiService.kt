package com.simbadev.youtubeclone.remote

import android.provider.MediaStore
import com.simbadev.youtubeclone.remote.model.PlayLists
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("playlists")
    fun getPlayList(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("key") apiKey: String,
        @Query("maxResults") maxResults: Int = 20
    ): Call<PlayLists>

    @GET("playlistItems")
    suspend fun playlistItems(
        @Query("key") key: String,
        @Query("part") part: String,
        @Query("playlistId") channelId: String,
        @Query("maxResults") maxResults : Int
    ): Response<MediaStore.Audio.Playlists>



}