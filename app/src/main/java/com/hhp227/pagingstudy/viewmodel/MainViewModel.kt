package com.hhp227.pagingstudy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.hhp227.pagingstudy.data.MainRepository

class MainViewModel(private val repository: MainRepository) : ViewModel() {
    fun getSampleData() = repository.getSampleDataStream().cachedIn(viewModelScope)
}