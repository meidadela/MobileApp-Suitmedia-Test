package com.example.suitmediatest.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.suitmediatest.data.remote.ApiService
import com.example.suitmediatest.data.response.DataItem

class UserPagingSource(private val apiService: ApiService) : PagingSource<Int, DataItem>() {
    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val responseData = apiService.getUsers(position, params.loadSize)
            val data = responseData.data
            val totalPages = responseData.totalPages

            val prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1
            val nextKey = if (position < totalPages && data.size >= params.loadSize) position + 1 else null

            LoadResult.Page(
                data = data,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}