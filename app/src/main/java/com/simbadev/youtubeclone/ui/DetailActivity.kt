package com.simbadev.youtubeclone.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.simbadev.youtubeclone.R
import com.simbadev.youtubeclone.base.BaseActivity
import com.simbadev.youtubeclone.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity<ActivityDetailBinding>() {


    override fun inflateViewBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    override val viewModel: MainViewModel by lazy{
        ViewModelProvider(this)[DetailViewModel::class.java]

        
    }

    override fun initClickListener() {
        super.initClickListener()
        val result = intent.getStringExtra( "key")
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
    }

}