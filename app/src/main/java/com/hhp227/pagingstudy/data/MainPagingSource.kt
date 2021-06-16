package com.hhp227.pagingstudy.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hhp227.pagingstudy.api.MainService
import com.hhp227.pagingstudy.model.SampleData
import java.io.IOException

class MainPagingSource(private val mainService: MainService) : PagingSource<Int, SampleData>() {
    override fun getRefreshKey(state: PagingState<Int, SampleData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SampleData> {
        val page: Int = params.key ?: 1
        val result = mainService.getData(page)
        return try {
            LoadResult.Page(
                data = result,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (result.isEmpty()) null else page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        }
    }
}