package com.example.healthyliving.comingsoon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.healthyliving.databinding.ActivityChatbotBinding

@Suppress("DEPRECATION")
class ChatbotActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatbotBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatbotBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Chatbot"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}