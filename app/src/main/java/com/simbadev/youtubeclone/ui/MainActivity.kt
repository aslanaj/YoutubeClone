package com.simbadev.youtubeclone.ui

import android.content.Intent
import android.os.Build.ID
import android.widget.Adapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.simbadev.youtubeclone.base.BaseActivity
import com.simbadev.youtubeclone.databinding.ActivityMainBinding
import com.simbadev.youtubeclone.model.Item
import com.simbadev.youtubeclone.ui.adapter.MainAdapter

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


        viewModel.getPlayList().observe(this) {
            Toast.makeText(this, it.kind.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun onClick(item: Item) {
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra("key", item.snippet.title)
        Toast.makeText(this, item.snippet.title, Toast.LENGTH_SHORT).show()
        startActivity(intent)

    }


}