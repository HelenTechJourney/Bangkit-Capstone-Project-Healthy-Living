package com.example.healthyliving.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.healthyliving.R
import com.example.healthyliving.comingsoon.ChatbotActivity
import com.example.healthyliving.comingsoon.KalkulatorActivity
import com.example.healthyliving.ui.authentication.Login
import com.example.healthyliving.databinding.FragmentProfileBinding
import com.example.healthyliving.di.ViewModelFactory
import com.example.healthyliving.remote.response.UserPreference
import com.example.healthyliving.ui.authentication.DataStoreViewModel

class ProfileFragment(private val dataStore: DataStore<Preferences>) : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private var isFinished = false

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().title = "Profile"
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pref = UserPreference.getInstance(dataStore)
        val viewModel =
            ViewModelProvider(this, ViewModelFactory(pref))[DataStoreViewModel::class.java]

        viewModel.getName().observe(viewLifecycleOwner) {
            binding.username.text = StringBuilder().append(it)
        }

        binding.edit.setOnClickListener {
//            val intent = Intent(requireContext(), EditActivity::class.java)
//            startActivity(intent)
        }
        binding.kalkulator.setOnClickListener{
            val intent = Intent(requireContext(), KalkulatorActivity::class.java)
            startActivity(intent)
        }
        binding.chatbot.setOnClickListener{
            val intent = Intent(requireContext(), ChatbotActivity::class.java)
            startActivity(intent)
        }
        binding.Logout.setOnClickListener{
            showAlertDialog()
        }
    }
    private fun showAlertDialog() {
        val dialogBuilder = AlertDialog.Builder(requireContext())
        val alert = dialogBuilder.create()
        dialogBuilder
            .setTitle(getString(R.string.logout))
            .setMessage(getString(R.string.you_sure))
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                logout()
            }
            .setNegativeButton(getString(R.string.no)) { _, _ ->
                alert.cancel()
            }
            .show()
    }

    private fun logout() {
        val pref = UserPreference.getInstance(dataStore)
        val viewModel =
            ViewModelProvider(this, ViewModelFactory(pref))[DataStoreViewModel::class.java]
        viewModel.apply {
            saveLoginState(false)
            saveToken("")
            saveName("")
        }
        isFinished = true
        startActivity(Intent(requireContext(), Login::class.java))
        requireActivity().finish()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}