package com.example.marchwhatsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.marchwhatsapp.databinding.FragmentsChatBinding

class ChatsFragment : Fragment() {

    private lateinit var binding: FragmentsChatBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentsChatBinding.inflate(inflater, container, false)
        return binding.root
    }
}