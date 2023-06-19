package com.simbadev.youtubeclone.ui.detailed

import androidx.lifecycle.LiveData
import com.simbadev.youtubeclone.App.Companion.repository
import com.simbadev.youtubeclone.core.result.Resource
import com.simbadev.youtubeclone.core.ui.BaseViewModel
import com.simbadev.youtubeclone.remote.model.PlaylistItems

class DetailViewModel : BaseViewModel() {
    fun getPlaylistItems(id: String): LiveData<Resource<PlaylistItems>> {
        return repository.getPlayListItems(id)
    }

}