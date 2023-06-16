package com.example.healthyliving.ui.main

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
import com.example.healthyliving.R
import com.example.healthyliving.databinding.FragmentJelajahiBinding
import com.example.healthyliving.di.RepoViewModelFactory
import com.example.healthyliving.di.ViewModelFactory
import com.example.healthyliving.remote.response.ArtikelItem
import com.example.healthyliving.remote.response.UserPreference
import com.example.healthyliving.ui.authentication.DataStoreViewModel
import com.example.healthyliving.ui.detail.DetailArticleActivity

class JelajahiFragment(private val dataStore: DataStore<Preferences>) : Fragment() {

    private lateinit var binding: FragmentJelajahiBinding
    private lateinit var token: String
    private val artikelViewModel: JelajahiViewModel by viewModels{
        RepoViewModelFactory(requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().title = "Jelajahi"
        binding = FragmentJelajahiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvJelajahi.layoutManager = layoutManager

        val bottomNavigationBarHeight = resources.getDimensionPixelSize(R.dimen.bottom_navigation_bar_height)
        val layoutParams = binding.root.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.setMargins(0, 0, 0, bottomNavigationBarHeight)
        binding.root.layoutParams = layoutParams

        val pref = UserPreference.getInstance(dataStore)
        val viewModel =
            ViewModelProvider(this, ViewModelFactory(pref))[DataStoreViewModel::class.java]

        viewModel.getToken().observe(viewLifecycleOwner) {
            token = it
            setData(it)
//            viewModel.getArticle(token)
        }
//        viewModel.listArticle.observe(viewLifecycleOwner) { listUser ->
//            listUser?.let{setData(it)}
//        }
//        viewModel.isLoading.observe(viewLifecycleOwner) {
//            showLoading(it)
//        }
    }

    private fun showSelectedArticle(detailArticle: ArtikelItem) {
        val intent = Intent(requireContext(), DetailArticleActivity::class.java)

        intent.putExtra(DetailArticleActivity.EXTRA_ARTICLE, detailArticle)
        startActivity(intent)
    }

    private fun setData(token:String) {
        val adapter = ArticleAdapter(requireContext())
        binding.rvJelajahi.visibility = View.VISIBLE
        binding.rvJelajahi.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )
        artikelViewModel.getArticle(token).observe(this) {
            adapter.submitData(lifecycle, it)
        }

        adapter.setOnItemClickCallback(object : ArticleAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ArtikelItem) {
                showSelectedArticle(data)
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}