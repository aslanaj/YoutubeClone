package com.simbadev.youtubeclone.ui.playlist

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.simbadev.youtubeclone.core.ui.BaseActivity
import com.simbadev.youtubeclone.databinding.ActivityMainBinding
import com.simbadev.youtubeclone.remote.model.Item
import com.simbadev.youtubeclone.core.utils.ConnectionLiveData
import com.simbadev.youtubeclone.ui.detailed.DetailActivity
import com.simbadev.youtubeclone.ui.playlist.adapter.MainAdapter

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private var adapter = MainAdapter(this::onClick)

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun inflateViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun setupLiveData() {
        super.setupLiveData()
        binding.rvPlayList.layoutManager = LinearLayoutManager(this)

        viewModel.getPlayList().observe(this) {
            binding.rvPlayList.adapter = adapter
            adapter.setList(it.items)
        }
    }

    private fun onClick(item: Item) {
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra("key", item.snippet.title)
        intent.putExtra("id", item.id)
        intent.putExtra("title", item.snippet.title)
        intent.putExtra("desc", item.snippet.description)
        intent.putExtra("count", item.contentDetails.itemCount)
        startActivity(intent)

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