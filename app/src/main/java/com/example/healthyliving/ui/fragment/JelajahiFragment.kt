package com.example.healthyliving.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.healthyliving.ui.activity.DetailArticleActivity
import com.example.healthyliving.ui.adapter.ArticleAdapter
import com.example.healthyliving.databinding.FragmentJelajahiBinding
import com.example.healthyliving.remote.response.UserPreference
import com.example.healthyliving.ui.viewmodel.LoginViewModel
import com.example.healthyliving.ui.viewmodel.ViewModelFactory
import com.example.storyapp.remote.response.ListStoryItem

class JelajahiFragment(private val dataStore: DataStore<Preferences>) : Fragment() {

    private lateinit var binding: FragmentJelajahiBinding

    private lateinit var token: String
//    private val viewModel: JelajahiViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().title = "Jelajahi"
        binding = FragmentJelajahiBinding.inflate(inflater, container, false)

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvJelajahi.layoutManager = layoutManager

        val pref = UserPreference.getInstance(dataStore)
        val loginViewModel =
            ViewModelProvider(this, ViewModelFactory(pref))[LoginViewModel::class.java]

//        loginViewModel.getToken().observe(viewLifecycleOwner) {
//            token = it
//            viewModel.getArticle(token)
//        }
//        viewModel.listUser.observe(viewLifecycleOwner) { listUser ->
//            listUser?.let{setData(it)}
//        }
//        viewModel.isLoading.observe(viewLifecycleOwner) {
//            showLoading(it)
//        }
        return binding.root
    }

//    private fun showSelectedArticle(detailArticle: ListStoryItem) {
//        val intent = Intent(requireContext(), DetailArticleActivity::class.java)
//
//        intent.putExtra(DetailArticleActivity.EXTRA_ARTICLE, detailArticle)
//        startActivity(intent)
//    }

    private fun setData(Items: List<ListStoryItem>) {
        val adapter = ArticleAdapter(Items)
        binding.rvJelajahi.adapter = adapter
        binding.rvJelajahi.visibility = View.VISIBLE

//        adapter.setOnItemClickCallback(object : ArticleAdapter.OnItemClickCallback {
//            override fun onItemClicked(data: ListStoryItem) {
//                showSelectedArticle(data)
//            }
//        })
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}