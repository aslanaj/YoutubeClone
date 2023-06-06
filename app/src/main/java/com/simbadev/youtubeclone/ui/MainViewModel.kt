package com.simbadev.youtubeclone.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.simbadev.youtubeclone.BuildConfig
import com.simbadev.youtubeclone.model.PlayLists
import com.simbadev.youtubeclone.remote.ApiService
import com.simbadev.youtubeclone.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel : ViewModel() {


    private val apiService: ApiService = RetrofitClient.create()


    fun getPlayList(): LiveData<PlayLists> {
        return playList()
    }

    private fun playList(): LiveData<PlayLists> {

        val data = MutableLiveData<PlayLists>()
        apiService.getPlayList(
            "snippet,contentDetails",
            "UCWOA1ZGywLbqmigxE4Qlvuw",
            BuildConfig.API_KEY
        ).enqueue(object : Callback<PlayLists> {
            override fun onResponse(call: Call<PlayLists>, response: Response<PlayLists>) {

                if (response.isSuccessful) {
                    data.value =  response.body()
                }
            }

            override fun onFailure(call: Call<PlayLists>, t: Throwable) {
                print(t.stackTrace)
                // 400 to 500
                // 400 - Not Foun , 401 - Доступ звпрвщен
            }

        })

        return data
    }
}