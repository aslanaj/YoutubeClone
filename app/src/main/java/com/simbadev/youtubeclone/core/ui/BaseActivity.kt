package com.simbadev.youtubeclone.core.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.simbadev.youtubeclone.core.utils.ConnectionLiveData

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: VB

    protected abstract fun inflateViewBinding(): VB
    protected lateinit var connectionLiveData: ConnectionLiveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateViewBinding()
        setContentView(binding.root)

        checkInternet()
        setUI()
        setupLiveData()
        initClickListener()
        connectionLiveData = ConnectionLiveData(this)

    }

    open fun setupLiveData() {} // инициализация Live data

    open fun setUI() {} // инициализация UI

    open fun initClickListener() {} // внутру этого метода обрабатываем все клики

    open fun checkInternet() {} // внутру этого метода проверяем интернет


}