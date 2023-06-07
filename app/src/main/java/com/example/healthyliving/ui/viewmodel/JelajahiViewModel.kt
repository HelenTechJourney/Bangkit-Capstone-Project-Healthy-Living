//package com.example.healthyliving.ui.viewmodel
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.paging.ExperimentalPagingApi
//import androidx.paging.Pager
//import androidx.paging.PagingConfig
//import androidx.paging.PagingData
//import com.example.healthyliving.remote.retrofit.ApiConfig
//import com.example.storyapp.remote.response.ListStoryItem
//import com.example.storyapp.remote.response.ListStoryResponse
//import retrofit2.Call
//import retrofit2.Response
//
//class JelajahiViewModel : ViewModel() {
//    private val _listUser = MutableLiveData<List<ListStoryItem>>()
//    val listUser: LiveData<List<ListStoryItem>?> = _listUser
//
//    private val _message = MutableLiveData<String>()
//    val message: LiveData<String> = _message
//
//    private val _isLoading = MutableLiveData<Boolean>()
//    val isLoading: LiveData<Boolean> = _isLoading
//
//    var isError: Boolean = false
////    fun getPagingStories(token:String): LiveData<PagingData<ListStoryItem>> {
////        @OptIn(ExperimentalPagingApi::class)
////        return Pager(
////            config = PagingConfig(
////                pageSize = 5
////            ),
////            remoteMediator = StoryRemoteMediator(storyDatabase, apiService, token),
////            pagingSourceFactory = {
////                storyDatabase.storyDao().getAllListStories()
////            }
////        ).liveData
////    }
//
//    fun getArticle(token: String) {
//        _isLoading.value = true
//        val api = ApiConfig.getApiService().getArticle("Bearer $token")
//        api.enqueue(object : retrofit2.Callback<ListStoryResponse> {
//            override fun onResponse(
//                call: Call<ListStoryResponse>,
//                response: Response<ListStoryResponse>
//            ) {
//                _isLoading.value = false
//                if (response.isSuccessful) {
//                    isError = false
//                    val responseBody = response.body()
//                    if (responseBody != null) {
//                        _listUser.value = responseBody.listStory
//                    }
//                    _message.value = responseBody?.message.toString()
//
//                } else {
//                    isError = true
//                    _message.value = response.message()
//                }
//            }
//
//            override fun onFailure(call: Call<ListStoryResponse>, t: Throwable) {
//                _isLoading.value = false
//                isError = true
//                _message.value = t.message.toString()
//            }
//
//        })
//    }
//}