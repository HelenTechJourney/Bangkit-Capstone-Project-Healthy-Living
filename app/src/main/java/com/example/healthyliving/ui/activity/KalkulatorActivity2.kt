package com.example.healthyliving.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.healthyliving.R
import com.example.healthyliving.databinding.ActivityKalkulator2Binding
import com.example.healthyliving.databinding.FragmentForm1Binding

class KalkulatorActivity2 : AppCompatActivity() {
    private lateinit var binding : ActivityKalkulator2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKalkulator2Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}