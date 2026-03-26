package com.example.marchwhatsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.marchwhatsapp.databinding.FragmentSignupBinding
import com.example.marchwhatsapp.databinding.FragmentsChatBinding
import com.example.marchwhatsapp.databinding.FragmentsStatusBinding

class StatusFragment : Fragment() {

    private lateinit var binding: FragmentsStatusBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentsStatusBinding.inflate(inflater, container, false)
        return binding.root
    }
}