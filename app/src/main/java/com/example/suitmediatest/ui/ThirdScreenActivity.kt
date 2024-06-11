package com.example.suitmediatest.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.suitmediatest.adapter.UsersAdapter
import com.example.suitmediatest.databinding.ActivityThirdScreenBinding
import com.example.suitmediatest.viewModel.ThirdViewModel
import com.example.suitmediatest.viewModel.ViewModelFactory
import kotlinx.coroutines.launch

class ThirdScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdScreenBinding
    private lateinit var usersAdapter: UsersAdapter
    private var name: String? = null
    private val viewModel by viewModels<ThirdViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        name = intent.getStringExtra(SecondScreenActivity.NAME)

        usersAdapter = UsersAdapter(name ?: "")
        binding.rvUsers.adapter = usersAdapter
        val layoutManager = LinearLayoutManager(this)
        binding.rvUsers.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvUsers.addItemDecoration(itemDecoration)

        binding.backButton.setOnClickListener {
            val intent = Intent(this, SecondScreenActivity::class.java)
            intent.putExtra(SecondScreenActivity.NAME, name)
            startActivity(intent)
            finish()
        }

        binding.progressBar.visibility = View.VISIBLE

        lifecycleScope.launch {
            viewModel.user.observe(this@ThirdScreenActivity) { result ->
                binding.progressBar.visibility = View.GONE
                usersAdapter.submitData(lifecycle, result)
            }
        }
    }
}