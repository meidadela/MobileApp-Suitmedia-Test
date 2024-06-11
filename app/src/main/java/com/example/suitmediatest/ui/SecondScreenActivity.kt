package com.example.suitmediatest.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.suitmediatest.databinding.ActivitySecondScreenBinding

class SecondScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(NAME)
        binding.userName.text = name

        val selectedUserName = intent.getStringExtra(SELECTED_USER_NAME)
        if (selectedUserName != null) {
            binding.selectedUserName.text = selectedUserName
        }

        binding.backButton.setOnClickListener {
            val intent = Intent(this, FirstScreenActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.chooseButton.setOnClickListener {
            val intent = Intent(this, ThirdScreenActivity::class.java)
            intent.putExtra(NAME, name)
            startActivity(intent)
        }
    }

    companion object {
        const val NAME = "name"
        const val SELECTED_USER_NAME = "selected_user_name"
    }
}