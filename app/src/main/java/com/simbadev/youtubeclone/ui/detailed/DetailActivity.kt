package com.simbadev.youtubeclone.ui.detailed

import android.widget.Toast
import com.simbadev.youtubeclone.core.ui.BaseActivity
import com.simbadev.youtubeclone.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity<ActivityDetailBinding>() {


    override fun inflateViewBinding(): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

  /*  override val viewModel: MainViewModel by lazy{
        ViewModelProvider(this)[DetailViewModel::class.java]


    }*/

    override fun initClickListener() {
        super.initClickListener()
        val result = intent.getStringExtra( "key")
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
    }

}