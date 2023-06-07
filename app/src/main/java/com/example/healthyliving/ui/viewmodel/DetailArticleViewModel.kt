//package com.example.healthyliving.ui.viewmodel
//
//import android.content.ContentValues
//import android.util.Log
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import com.example.healthyliving.remote.retrofit.ApiConfig
//import com.example.storyapp.remote.response.DetailResponse
//import com.example.storyapp.remote.response.Story
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class DetailArticleViewModel {
//    private val _detailUser = MutableLiveData<Story>()
//    val detailUser: LiveData<Story> = _detailUser
//
//    private val _message = MutableLiveData<String>()
//    val message: LiveData<String> = _message
//
//    private val _isLoading = MutableLiveData<Boolean>()
//    val isLoading: LiveData<Boolean> = _isLoading
//
//    fun getDetail(token: String, id:String) {
//        _isLoading.value = true
//        val api = ApiConfig.getApiService().getDetailArticle("Bearer $token", id)
//
//        api.enqueue(object : Callback<DetailResponse> {
//            override fun onResponse(
//                call: Call<DetailResponse>,
//                response: Response<DetailResponse>
//            ) {
//                _isLoading.value = false
//                if (response.isSuccessful) {
//                    val responseBody = response.body()
//                    _detailUser.value = responseBody?.story
//                } else {
//                    Log.e(ContentValues.TAG, "onFailure1: ${response.message()}")
//                }
//            }
//
//            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
//                _isLoading.value = false
//                Log.e(ContentValues.TAG, "onFailure2: ${t.message}")
//            }
//        })
//    }
//}