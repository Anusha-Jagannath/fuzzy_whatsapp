package com.example.marchwhatsapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.marchwhatsapp.databinding.ActivityChatBinding

class ChatsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menu1 = MenuInflater(this).inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
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
                Toast.makeText(this, "clicked on item3", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}