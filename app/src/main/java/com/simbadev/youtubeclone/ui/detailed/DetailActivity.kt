package com.simbadev.youtubeclone.ui.detailed

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.simbadev.youtubeclone.R
import com.simbadev.youtubeclone.core.result.Resource
import com.simbadev.youtubeclone.core.ui.BaseActivity
import com.simbadev.youtubeclone.core.utils.ConnectionLiveData
import com.simbadev.youtubeclone.databinding.ActivityDetailBinding
import com.simbadev.youtubeclone.ui.playlist.MainActivity.Companion.KEY_FOR_COUNT_OF_VIDEOS
import com.simbadev.youtubeclone.ui.playlist.MainActivity.Companion.KEY_FOR_DESCRIPTION
import com.simbadev.youtubeclone.ui.playlist.MainActivity.Companion.KEY_FOR_ID
import com.simbadev.youtubeclone.ui.playlist.MainActivity.Companion.KEY_FOR_TITLE

class DetailActivity() : BaseActivity<ActivityDetailBinding, DetailViewModel>() {

    private val adapter = DetailAdapter()

    override val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this)[DetailViewModel::class.java]
    }

    override fun inflateViewBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun setUI() {
        super.setUI()
        with(binding) {
            tvTitle.text = intent.getStringExtra(KEY_FOR_TITLE)
            tvDesc.text = intent.getStringExtra(KEY_FOR_DESCRIPTION)
            tvCount.text = String.format(
                getString(R.string.count),
                intent.getStringExtra(KEY_FOR_COUNT_OF_VIDEOS)
            )
        }
    }

    override fun setupLiveData() {
        super.setupLiveData()
        viewModel.loading.observe(this) {
            binding.progressBar.isVisible = it
        }

        intent.getStringExtra(KEY_FOR_ID)?.let {
            viewModel.getPlaylistItems(it).observe(this) {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        binding.rvPlayLists.adapter = adapter
                        it.data?.let { it1 -> adapter.setList(it1.items)
                            Log.e("ololo", "success: ${it1.items}", )}
                        viewModel.loading.postValue(false)
                    }

                    Resource.Status.ERROR -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                        Log.e("ololo", "error: ${it.data}", )
                        viewModel.loading.postValue(false)
                    }

                    Resource.Status.LOADING -> {
                        Log.e("kamino", "loading: ${it.data}", )
                        viewModel.loading.postValue(true)
                    }
                }
            }
        }
    }

    override fun checkInternet() {
        super.checkInternet()
        ConnectionLiveData(application).observe(this){
            with(binding) {
                if (it){
                    layoutInclude.root.visibility = View.GONE
                }else{
                    layoutInclude.root.visibility = View.VISIBLE
                }
            }
        }
    }
}