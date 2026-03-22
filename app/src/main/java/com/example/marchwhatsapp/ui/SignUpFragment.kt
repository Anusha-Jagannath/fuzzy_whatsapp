package com.example.marchwhatsapp.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.marchwhatsapp.R
import com.example.marchwhatsapp.databinding.FragmentSignupBinding
import com.example.marchwhatsapp.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var progressDialog: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        progressDialog = ProgressDialog(requireContext())
        progressDialog.setTitle("Loading")
        progressDialog.setMessage("Please wait...")

        initFirebase()
        initClickListeners()
        return binding.root
    }

    private fun initClickListeners() {
        binding.loginTv.setOnClickListener {
            replaceFragment(LoginFragment())
        }

        binding.registerBtn.setOnClickListener {
            progressDialog.show()
            val name = binding.usernameEdittext.text.toString()
            val email = binding.emailEdittext.text.toString()
            val pass = binding.passwordEdittext.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty()) {

                firebaseAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener { it ->
                        if (it.isSuccessful) {
                            progressDialog.dismiss()
                            Toast.makeText(
                                requireContext(), "user registered successfully", Toast.LENGTH_SHORT
                            ).show()
                            val user = User(
                                userId = it.result.user?.uid.toString(),
                                userName = name,
                                email = email,
                                profilePic = ""
                            )

                            if (user != null) {
                                saveUserToDatabase(user)
                            }
                        } else {
                            Toast.makeText(
                                requireContext(), "user registration failed", Toast.LENGTH_SHORT
                            ).show()
                        }

                    }.addOnFailureListener {

                    }

            } else {
                Toast.makeText(requireContext(), "please enter valid data", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction().replace(R.id.fContainer, fragment).commit()
    }

    private fun initFirebase() {
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
    }

    private fun saveUserToDatabase(user: User) {
        firebaseDatabase.reference
            .child("Users").child(user.userId)
            .setValue(user)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d("Test", "user saved successfully")

                }
            }
    }
}