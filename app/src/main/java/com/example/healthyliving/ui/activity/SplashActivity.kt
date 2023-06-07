package com.example.healthyliving.ui.activity

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewPropertyAnimator
import androidx.lifecycle.ViewModelProvider
import com.example.healthyliving.ui.activity.FormActivity
import com.example.healthyliving.R
import com.example.healthyliving.databinding.ActivitySplashBinding
import com.example.healthyliving.remote.response.UserPreference
import com.example.healthyliving.ui.viewmodel.LoginViewModel
import com.example.healthyliving.ui.viewmodel.ViewModelFactory

@Suppress("DEPRECATION")
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
        val loginViewModel =
            ViewModelProvider(this, ViewModelFactory(pref))[LoginViewModel::class.java]

        loginViewModel.getLoginState().observe(this) {
            ObjectAnimator.ofFloat(binding.splashImage, View.ALPHA, 1f).apply {
                start()
            }
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
    //        android.os.Handler().postDelayed({
//            startActivity(Intent(this, FormActivity::class.java))
//            finish()
//        }, 3000)
    override fun onDestroy() {
        startSplash?.cancel()
        super.onDestroy()
    }
}