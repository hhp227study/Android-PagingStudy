package com.hhp227.pagingstudy.data

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hhp227.pagingstudy.api.MainService
import com.hhp227.pagingstudy.model.SampleData
import kotlinx.coroutines.flow.Flow

class MainRepository(private val context: Context) {
    fun getSampleDataStream(): Flow<PagingData<SampleData>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 10),
            pagingSourceFactory = { MainPagingSource(MainService(context.assets)) }
        ).flow
    }

    companion object {
        @Volatile private var instance: MainRepository? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: MainRepository(context).also { instance = it }
            }
    }
}