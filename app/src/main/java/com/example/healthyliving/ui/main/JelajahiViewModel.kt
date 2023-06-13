package com.example.healthyliving.ui.main

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.healthyliving.remote.response.ArticleResponse
import com.example.healthyliving.remote.response.ArtikelItem
import com.example.healthyliving.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JelajahiViewModel : ViewModel() {
    private val _listArticle = MutableLiveData<List<ArtikelItem>>()
    val listArticle: LiveData<List<ArtikelItem>?> = _listArticle

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    var isError: Boolean = false
//    fun getPagingStories(token:String): LiveData<PagingData<ListStoryItem>> {
//        @OptIn(ExperimentalPagingApi::class)
//        return Pager(
//            config = PagingConfig(
//                pageSize = 5
//            ),
//            remoteMediator = StoryRemoteMediator(storyDatabase, apiService, token),
//            pagingSourceFactory = {
//                storyDatabase.storyDao().getAllListStories()
//            }
//        ).liveData
//    }

    fun getArticle(token: String) {
        _isLoading.value = true
        val api = ApiConfig.getApiService().getArticle("Bearer $token")
        api.enqueue(object : Callback<ArticleResponse> {
            override fun onResponse(
                call: Call<ArticleResponse>,
                response: Response<ArticleResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    isError = false
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _listArticle.value = responseBody.data
                    } else {
                        Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }
}