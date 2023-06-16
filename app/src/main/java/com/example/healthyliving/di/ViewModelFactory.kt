package com.example.healthyliving.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.healthyliving.remote.response.UserPreference
import com.example.healthyliving.ui.authentication.DataStoreViewModel
import com.example.healthyliving.ui.main.JelajahiViewModel
import com.example.healthyliving.ui.main.ResepViewModel

class ViewModelFactory(private val pref: UserPreference) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DataStoreViewModel::class.java)) {
            return DataStoreViewModel(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}

class RepoViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when{
            modelClass.isAssignableFrom(JelajahiViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                return JelajahiViewModel(Injection.provideRepository1(context)) as T
            }
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
