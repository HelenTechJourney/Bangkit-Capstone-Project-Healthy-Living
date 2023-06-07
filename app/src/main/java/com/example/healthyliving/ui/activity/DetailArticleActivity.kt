//package com.example.healthyliving.ui.activity
//
//import android.os.Build
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.view.View
//import androidx.activity.viewModels
//import androidx.lifecycle.ViewModelProvider
//import com.bumptech.glide.Glide
//import com.example.healthyliving.databinding.ActivityDetailArticleBinding
//import com.example.healthyliving.remote.response.UserPreference
//import com.example.healthyliving.ui.viewmodel.DetailArticleViewModel
//import com.example.healthyliving.ui.viewmodel.LoginViewModel
//import com.example.healthyliving.ui.viewmodel.ProfileViewModel
//import com.example.healthyliving.ui.viewmodel.ViewModelFactory
//import com.example.storyapp.remote.response.ListStoryItem
//import com.example.storyapp.remote.response.Story
//
//class DetailArticleActivity : AppCompatActivity() {
////    private val profileViewModel : DetailArticleViewModel by viewModels()
//    private lateinit var binding: ActivityDetailArticleBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityDetailArticleBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        supportActionBar?.title = "Detail Story"
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//
////        val story = if (Build.VERSION.SDK_INT >= 33) {
////            intent.getParcelableExtra(EXTRA_ARTICLE, ListStoryItem::class.java)
////        } else {
////            intent.getParcelableExtra(EXTRA_ARTICLE)
////        }
////
////        story?.let{story->
////            val pref = UserPreference.getInstance(dataStore)
////            val dataStoreViewModel =
////                ViewModelProvider(this, ViewModelFactory(pref))[LoginViewModel::class.java]
////            dataStoreViewModel.getToken().observe(this) {token->
////                viewModel.getDetail(token,story.id)
////            }
////        }
////
////        viewModel.isLoading.observe(this) {
////            showLoading(it)
////        }
////        viewModel.detailUser.observe(this) { detailUser ->
////            detailUser?.let{setDetailStory(it)}
////        }
//    }
////
////    private fun setDetailStory(detailStory: Story) {
////        binding.apply {
////            tvDetailName.text = detailStory.name
////            tvDetailDescription.text = detailStory.description
////        }
////        Glide.with(this)
////            .load(detailStory.photoUrl)
////            .into(binding.ivDetailPhoto)
////    }
////
////    private fun showLoading(isLoading: Boolean) {
////        if (isLoading) {
////            binding.progressBar.visibility = View.VISIBLE
////        } else {
////            binding.progressBar.visibility = View.GONE
////        }
////    }
////
////    override fun onSupportNavigateUp(): Boolean {
////        onBackPressed()
////        return true
////    }
//
//    companion object {
//        const val EXTRA_ARTICLE = "extra_article"
//    }
//}