package com.simbadev.youtubeclone.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.simbadev.youtubeclone.BuildConfig
import com.simbadev.youtubeclone.core.network.RetrofitClient
import com.simbadev.youtubeclone.core.result.Resource
import com.simbadev.youtubeclone.core.utils.channelId
import com.simbadev.youtubeclone.core.utils.part
import com.simbadev.youtubeclone.remote.ApiService
import com.simbadev.youtubeclone.remote.model.PlayList
import com.simbadev.youtubeclone.remote.model.PlayLists
import com.simbadev.youtubeclone.remote.model.PlaylistItems
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class Repository {
    private val apiService: ApiService = RetrofitClient.create()

    fun getPlayLists(): LiveData<Resource<PlayLists>> {
        val data = MutableLiveData<Resource<PlayLists>>()

        data.value = Resource.loading()

        apiService.getPlayList(part, BuildConfig.API_KEY, channelId)
            .enqueue(object : retrofit2.Callback<PlayLists> {
                override fun onResponse(call: Call<PlayLists>, response: Response<PlayLists>) {
                    if (response.isSuccessful){
                        data.value = Resource.success(response.body())
                    }
                }

                override fun onFailure(call: Call<PlayLists>, t: Throwable) {
                    data.value = t.message?.let { Resource.error(it, null, null) }
                }

            })
        return data
    }


    fun getPlayListItems(id: String): LiveData<Resource<PlaylistItems>> {
        val data = MutableLiveData<Resource<PlaylistItems>>()

        data.value = Resource.loading()


        apiService.getPlayListItems(part, BuildConfig.API_KEY, playlistId = id)
            .enqueue(object : retrofit2.Callback<PlaylistItems> {
                override fun onResponse(
                    call: Call<PlaylistItems>, response: Response<PlaylistItems>
                ) {
                    if (response.isSuccessful) {
                        data.value = Resource.success(response.body())
                    }
                }

                override fun onFailure(call: Call<PlaylistItems>, t: Throwable) {
                    data.value = t.message?.let { Resource.error(it, null, null) }
                }

            })
        return data
    }


}