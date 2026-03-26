package com.example.marchwhatsapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.marchwhatsapp.adapter.FragmentViewPagerAdapter
import com.example.marchwhatsapp.databinding.ActivityChatBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class ChatsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViewPager()
        initGso()
        setSupportActionBar(binding.toolBar)
        auth = FirebaseAuth.getInstance()
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menu1 = MenuInflater(this).inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun initGso() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.group_chat -> {
                Toast.makeText(this, "clicked on item1", Toast.LENGTH_SHORT).show()
            }

            R.id.settings -> {
                Toast.makeText(this, "clicked on item2", Toast.LENGTH_SHORT).show()
            }

            R.id.logout -> {
                auth.signOut()
                mGoogleSignInClient.signOut()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "user logged out successfully", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUpViewPager() {
        binding.viewPager.adapter = FragmentViewPagerAdapter(supportFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }
}