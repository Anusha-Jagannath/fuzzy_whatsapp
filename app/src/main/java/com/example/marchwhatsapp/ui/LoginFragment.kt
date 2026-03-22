package com.example.marchwhatsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.marchwhatsapp.R
import com.example.marchwhatsapp.databinding.FragmentLoginBinding
import com.example.marchwhatsapp.databinding.FragmentSignupBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        initClickListeners()
        return binding.root
    }

    private fun initClickListeners() {
        binding.loginTv.setOnClickListener {
            replaceFragment(SignUpFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction().replace(R.id.fContainer, fragment).commit()
    }
}