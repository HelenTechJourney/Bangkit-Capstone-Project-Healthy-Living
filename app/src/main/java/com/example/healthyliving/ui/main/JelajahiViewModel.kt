package com.example.healthyliving.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.healthyliving.data.Repository
import com.example.healthyliving.remote.response.ArtikelItem

class JelajahiViewModel(private val provideRepository: Repository) : ViewModel() {
    fun getArticle(token:String): LiveData<PagingData<ArtikelItem>> {
        return provideRepository.getArticle(token).cachedIn(viewModelScope)}

}