package com.example.healthyliving.ui.form

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.healthyliving.databinding.FragmentForm3Binding
import com.example.healthyliving.di.ViewModelFactory
import com.example.healthyliving.remote.response.UserPreference
import com.example.healthyliving.ui.authentication.DataStoreViewModel

class Form3Fragment(private val dataStore: DataStore<Preferences>) : Fragment() {
    private lateinit var binding: FragmentForm3Binding
    private val formViewModel: FormViewModel by viewModels()
    private lateinit var token: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForm3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pref = UserPreference.getInstance(dataStore)
        val loginViewModel =
            ViewModelProvider(this, ViewModelFactory(pref))[DataStoreViewModel::class.java]

        loginViewModel.getToken().observe(viewLifecycleOwner) {
            token = it
            formViewModel.getResult(token)
        }
    }



}