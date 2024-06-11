package com.example.suitmediatest.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.suitmediatest.databinding.ActivityFirstScreenBinding

class FirstScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCheck.setOnClickListener {
            val palindrome = binding.etPalindrome.text.toString()
            val isPalindrome = isPalindrome(palindrome)
            val message = if (isPalindrome) "Is Palindrome" else "Not Palindrome"

            val alertDialog = AlertDialog.Builder(this)
                .setTitle("Palindrome Check")
                .setMessage(message)
                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
                .create()

            alertDialog.show()
        }

        binding.btnNext.setOnClickListener {
            val name = binding.etName.text.toString()

            if (name.isEmpty()){
                Toast.makeText(this, "Fill the name", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, SecondScreenActivity::class.java)
                intent.putExtra(SecondScreenActivity.NAME, name)
                startActivity(intent)
            }
        }
    }

    private fun isPalindrome(text: String): Boolean {
        val cleanText = text.replace("\\s".toRegex(), "")
        val reverseText = cleanText.reversed()
        return cleanText.equals(reverseText, ignoreCase = true)
    }
}