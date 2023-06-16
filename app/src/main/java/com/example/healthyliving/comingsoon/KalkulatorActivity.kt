package com.example.healthyliving.comingsoon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.healthyliving.databinding.ActivityKalkulatorBinding

@Suppress("DEPRECATION")
class KalkulatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKalkulatorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKalkulatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Kalkulator"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}