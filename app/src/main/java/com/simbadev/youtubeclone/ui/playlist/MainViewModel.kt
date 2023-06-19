package com.simbadev.youtubeclone.ui.playlist

import androidx.lifecycle.LiveData
import com.simbadev.youtubeclone.App.Companion.repository
import com.simbadev.youtubeclone.remote.model.PlayLists
import com.simbadev.youtubeclone.core.result.Resource
import com.simbadev.youtubeclone.core.ui.BaseViewModel


class MainViewModel : BaseViewModel() {

    fun getPlaylists(): LiveData<Resource<PlayLists>> {
        return repository.getPlayLists()
    }


}