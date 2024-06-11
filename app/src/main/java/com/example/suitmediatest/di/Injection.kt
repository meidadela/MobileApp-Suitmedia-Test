package com.example.suitmediatest.di

import android.content.Context
import com.example.suitmediatest.data.UserRepository
import com.example.suitmediatest.data.remote.ApiConfig

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiService()
        return UserRepository.getInstance(apiService)
    }
}