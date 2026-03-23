package com.example.marchwhatsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.marchwhatsapp.databinding.ActivityChatBinding

class ChatsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}