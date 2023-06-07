package com.example.healthyliving.ui.viewmodel

import androidx.lifecycle.*
import com.example.healthyliving.remote.response.LoginResponse
import com.example.healthyliving.remote.response.UserPreference
import com.example.healthyliving.remote.retrofit.ApiConfig
import com.example.healthyliving.remote.response.RequestLogin
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class LoginViewModel(private val pref: UserPreference) : ViewModel() {
    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _userLogin = MutableLiveData<LoginResponse>()
    val userLogin: LiveData<LoginResponse> = _userLogin

    var isError: Boolean = false

    fun getLoginResponse(requestLogin: RequestLogin) {
        _isLoading.value = true
        val api = ApiConfig.getApiService().accessAccount(requestLogin)
        api.enqueue(object : retrofit2.Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                _isLoading.value = false
                val responseBody = response.body()

                if (response.isSuccessful) {
                    isError = false
                    _userLogin.value = responseBody!!
                    _message.value = "Login as ${_userLogin.value!!.resultLogin?.nama}"
                } else {
                    isError = true
                    _message.value = response.message()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                isError = true
                _isLoading.value = false
                _message.value=t.message.toString()
            }

        })
    }

    fun getLoginState(): LiveData<Boolean> {
        return pref.getLoginState().asLiveData()
    }

    fun saveLoginState(loginState: Boolean) {
        viewModelScope.launch {
            pref.saveLoginState(loginState)
        }
    }

    fun getToken(): LiveData<String> {
        return pref.getToken().asLiveData()
    }

    fun saveToken(token: String) {
        viewModelScope.launch {
            pref.saveToken(token)
        }
    }
    fun getName(): LiveData<String> {
        return pref.getName().asLiveData()
    }
    fun saveName(token: String) {
        viewModelScope.launch {
            pref.saveName(token)
        }
    }
}
class ViewModelFactory(private val pref: UserPreference) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}