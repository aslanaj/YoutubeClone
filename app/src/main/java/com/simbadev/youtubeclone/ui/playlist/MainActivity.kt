package com.simbadev.youtubeclone.ui.playlist

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.simbadev.youtubeclone.core.result.Resource
import com.simbadev.youtubeclone.core.ui.BaseActivity
import com.simbadev.youtubeclone.databinding.ActivityMainBinding
import com.simbadev.youtubeclone.remote.model.Item
import com.simbadev.youtubeclone.core.utils.ConnectionLiveData
import com.simbadev.youtubeclone.ui.detailed.DetailActivity
import com.simbadev.youtubeclone.ui.playlist.adapter.MainAdapter

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private var adapter = MainAdapter(this::onClick)

    override val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun inflateViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun setupLiveData() {
        super.setupLiveData()

        viewModel.loading.observe(this) {
       //     binding.progressBar.isVisible = it
        }

        viewModel.getPlaylists().observe(this) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.rvPlayList.adapter = adapter
                    it.data?.let { it1 -> adapter.setList(it1.items) }
                }
                Resource.Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    viewModel.loading.postValue(false)
                }

                Resource.Status.LOADING-> {
                    viewModel.loading.postValue(true)
                }
            }

        }
    }

    private fun onClick(item: Item) {
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra(KEY_FOR_KEY, item.snippet.title)
        intent.putExtra(KEY_FOR_ID, item.id)
        intent.putExtra(KEY_FOR_TITLE, item.snippet.title)
        intent.putExtra(KEY_FOR_DESCRIPTION, item.snippet.description)
        intent.putExtra(KEY_FOR_COUNT_OF_VIDEOS, item.contentDetails.itemCount)
        startActivity(intent)

    }

    override fun checkInternet() {
        super.checkInternet()
        ConnectionLiveData(application).observe(this) {
            with(binding) {
                if (it) {
                    layoutInclude.root.visibility = View.GONE
                } else {
                    layoutInclude.root.visibility = View.VISIBLE
                }
            }
        }
    }

    companion object{
        const val KEY_FOR_TITLE = "title"
        const val KEY_FOR_DESCRIPTION = "description"
        const val KEY_FOR_ID = "id"
        const val KEY_FOR_COUNT_OF_VIDEOS = "countOfVideos"
        const val KEY_FOR_KEY = "key"

    }


}