package com.hhp227.pagingstudy.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.hhp227.pagingstudy.R
import com.hhp227.pagingstudy.adapter.SampleListAdapter
import com.hhp227.pagingstudy.adapter.SampleLoadStateAdapter
import com.hhp227.pagingstudy.data.MainRepository
import com.hhp227.pagingstudy.viewmodel.MainViewModel
import com.hhp227.pagingstudy.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProvider(this, MainViewModelFactory(MainRepository.getInstance(this))).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        recycler_view.apply {
            adapter = SampleListAdapter().apply {
                withLoadStateFooter(SampleLoadStateAdapter())
                lifecycleScope.launch {
                    viewModel.getSampleData().collect {
                        submitData(it)
                    }
                }
            }
        }
    }
}