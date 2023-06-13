package com.example.healthyliving.ui.form

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.healthyliving.databinding.ActivityFormBinding

@Suppress("DEPRECATION")
class FormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btBack.setOnClickListener {
            onBackPressed()
        }
    }
}