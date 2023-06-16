package com.example.healthyliving.data

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.example.healthyliving.remote.retrofit.ApiService
import com.example.healthyliving.remote.response.ArtikelItem
import com.example.healthyliving.remote.response.ResepItem

class Repository(private val database: UserDatabase, private val apiService: ApiService) {
    fun getArticle(token:String): LiveData<PagingData<ArtikelItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                ArticlePagingSource(apiService, token)
            }
        ).liveData
    }
}