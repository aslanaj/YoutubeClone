package com.simbadev.youtubeclone

import android.app.Application
import com.simbadev.youtubeclone.repository.Repository

class App: Application() {
    companion object{
        val repository = Repository()
    }
}