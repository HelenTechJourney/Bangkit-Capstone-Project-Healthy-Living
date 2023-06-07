package com.example.healthyliving.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.healthyliving.databinding.ActivityFormBinding
import com.example.healthyliving.R
import com.example.healthyliving.ui.fragment.Form1Fragment

class FormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Form"
        val fragmentManager = supportFragmentManager
        val form1Fragment = Form1Fragment()
        val fragment = fragmentManager.findFragmentByTag(Form1Fragment::class.java.simpleName)
        if (fragment !is Form1Fragment) {
            Log.d("HealthyLivingApp", "Fragment Name :" + Form1Fragment::class.java.simpleName)
            fragmentManager
                .beginTransaction()
                .add(R.id.frame_container, form1Fragment, Form1Fragment::class.java.simpleName)
                .commit()
        }
    }
}