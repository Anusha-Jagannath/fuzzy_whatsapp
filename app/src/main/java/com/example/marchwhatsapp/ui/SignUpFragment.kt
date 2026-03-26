package com.example.marchwhatsapp.ui

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.marchwhatsapp.ChatsActivity
import com.example.marchwhatsapp.R
import com.example.marchwhatsapp.databinding.FragmentSignupBinding
import com.example.marchwhatsapp.model.User
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var progressDialog: ProgressDialog
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    companion object {
        private const val RC_SIGN_IN = 9001
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        progressDialog = ProgressDialog(requireContext())
        progressDialog.setTitle("Loading")
        progressDialog.setMessage("Please wait...")
        initGso()
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

        binding.googleBtn.setOnClickListener {
            signIn()
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
                    val intent = Intent(requireContext(), ChatsActivity::class.java)
                    startActivity(intent)
                }
            }
    }


    private fun initGso() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
    }

    private fun signIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }


    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                account?.let { it ->

                    val user = User(
                        userId = account.id ?: "",
                        userName = account.displayName ?: "",
                        email = account.email ?: "",
                        profilePic = account.photoUrl.toString()
                    )
                    saveUserToDatabase(user)
                }
            } catch (e: ApiException) {
                Toast.makeText(
                    requireContext(),
                    "Google sign in failed: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}