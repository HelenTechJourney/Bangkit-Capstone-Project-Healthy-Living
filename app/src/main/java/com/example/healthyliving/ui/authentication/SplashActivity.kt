package com.example.healthyliving.ui.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewPropertyAnimator
import androidx.lifecycle.ViewModelProvider
import com.example.healthyliving.databinding.ActivitySplashBinding
import com.example.healthyliving.di.ViewModelFactory
import com.example.healthyliving.remote.response.UserPreference
import com.example.healthyliving.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private var startSplash: ViewPropertyAnimator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val img = binding.splashImage
        val pref = UserPreference.getInstance(dataStore)
        val viewModel =
            ViewModelProvider(this, ViewModelFactory(pref))[DataStoreViewModel::class.java]

        viewModel.getLoginState().observe(this) {
            startSplash = img.animate().setDuration(3000L).alpha(1f).withEndAction {
                if (it) {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    startActivity(Intent(this, Login::class.java))
                    finish()
                }
            }
        }
    }
    override fun onDestroy() {
        startSplash?.cancel()
        super.onDestroy()
    }
}