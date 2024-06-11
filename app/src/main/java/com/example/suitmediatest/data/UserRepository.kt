package com.example.suitmediatest.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.suitmediatest.data.paging.UserPagingSource
import com.example.suitmediatest.data.remote.ApiService
import com.example.suitmediatest.data.response.DataItem
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiService: ApiService
) {
    fun getQuote(): LiveData<PagingData<DataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = {
                UserPagingSource(apiService)
            }
        ).liveData
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            apiService: ApiService
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService)
            }.also { instance = it }
    }
}