package com.example.healthyliving.di

import android.content.Context
import com.example.healthyliving.data.UserDatabase
import com.example.healthyliving.data.Repository
import com.example.healthyliving.remote.retrofit.ApiConfig

object Injection {
    fun provideRepository1(context: Context): Repository {
        val userDatabase = UserDatabase.getDatabase(context)
        val apiService = ApiConfig.getApiService()
        return Repository(userDatabase, apiService)
    }
}