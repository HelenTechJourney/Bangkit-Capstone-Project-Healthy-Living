package com.example.healthyliving.ui.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.healthyliving.remote.response.RecipeResponse
import com.example.healthyliving.remote.response.ResepItem
import com.example.healthyliving.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResepViewModel : ViewModel() {
    private val _listRecipe = MutableLiveData<List<ResepItem>>()
    val listRecipe: LiveData<List<ResepItem>?> = _listRecipe

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

    fun getRecipe(token: String) {
        _isLoading.value = true
        val api = ApiConfig.getApiService().getRecipe("Bearer $token")
        api.enqueue(object : Callback<RecipeResponse> {
            override fun onResponse(
                call: Call<RecipeResponse>,
                response: Response<RecipeResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    isError = false
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _listRecipe.value = responseBody.data
                    } else {
                        Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<RecipeResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }
}