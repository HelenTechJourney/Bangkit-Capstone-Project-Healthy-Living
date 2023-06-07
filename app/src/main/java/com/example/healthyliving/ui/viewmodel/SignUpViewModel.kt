package com.example.healthyliving.ui.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.healthyliving.remote.response.RequestSignup
import com.example.healthyliving.remote.retrofit.ApiConfig
import com.example.healthyliving.remote.retrofit.ResponseMessage
import retrofit2.Call
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    var isError: Boolean = false
    fun getSignupResponse(requestSignup: RequestSignup) {
        _isLoading.value = true
        val api = ApiConfig.getApiService().createAccount(requestSignup)
        api.enqueue(object : retrofit2.Callback<ResponseMessage> {
            override fun onResponse(
                call: Call<ResponseMessage>,
                response: Response<ResponseMessage>
            ) {
                _isLoading.value = false
                val responseBody = response.body()
                if (response.isSuccessful) {
                    isError = false
                    _message.value = responseBody?.message.toString()
                } else {
                    isError = true
                    _message.value = response.message() }
            }
            override fun onFailure(call: Call<ResponseMessage>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }
}