package com.example.suitmediatest.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.suitmediatest.data.UserRepository
import com.example.suitmediatest.data.response.DataItem

class ThirdViewModel(userRepository: UserRepository): ViewModel() {
    val user: LiveData<PagingData<DataItem>> =
        userRepository.getQuote().cachedIn(viewModelScope)
}