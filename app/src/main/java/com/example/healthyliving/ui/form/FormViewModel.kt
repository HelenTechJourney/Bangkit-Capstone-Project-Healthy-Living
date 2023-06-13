package com.example.healthyliving.ui.form

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.healthyliving.remote.response.*
import com.example.healthyliving.remote.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FormViewModel : ViewModel(){
    private val _listResult = MutableLiveData<List<Kalkulator>>()
    val listResult: LiveData<List<Kalkulator>> = _listResult

    private val _result = MutableLiveData<List<ResultForm>>()
    val result: LiveData<List<ResultForm>> = _result

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getFormResponse(requestForm: RequestForm) {
        _isLoading.value = true
        val api = ApiConfig.getApiService().createData(requestForm)
        api.enqueue(object : Callback<ResponseCalculator> {
            override fun onResponse(call: Call<ResponseCalculator>, response: Response<ResponseCalculator>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listResult.value = response.body()?.kalkulator
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseCalculator>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getResult(token: String) {
        _isLoading.value = true
        val api = ApiConfig.getApiService().getResult("Bearer $token")
        api.enqueue(object : Callback<ResultResponse> {
            override fun onResponse(
                call: Call<ResultResponse>,
                response: Response<ResultResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _result.value = responseBody.result
                    } else {
                        Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<ResultResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }
}