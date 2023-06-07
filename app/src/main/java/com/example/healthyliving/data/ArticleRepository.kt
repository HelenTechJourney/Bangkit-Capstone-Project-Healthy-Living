//package com.example.healthyliving.data
//
//import androidx.lifecycle.LiveData
//import androidx.paging.Pager
//import androidx.paging.PagingConfig
//import androidx.paging.PagingData
//import androidx.paging.liveData
//import com.example.healthyliving.remote.retrofit.ApiService
//import com.example.storyapp.remote.response.ListStoryItem
//
//class ArticleRepository (private val quoteDatabase: QuoteDatabase, private val apiService: ApiService) {
//    fun getQuote(): LiveData<PagingData<ListStoryItem>> {
//        return Pager(
//            config = PagingConfig(
//                pageSize = 5
//            ),
//            pagingSourceFactory = {
//                ArticlePagingSource(token, apiService)
//            }
//        ).liveData
//    }
//}