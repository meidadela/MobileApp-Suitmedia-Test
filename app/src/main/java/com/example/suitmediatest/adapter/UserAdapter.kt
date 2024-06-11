package com.example.suitmediatest.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.suitmediatest.data.response.DataItem
import com.example.suitmediatest.databinding.ItemRowUserBinding
import com.example.suitmediatest.ui.SecondScreenActivity

class UsersAdapter(private val name: String): PagingDataAdapter<DataItem, UsersAdapter.MyViewHolder>(DIFF_CALLBACK) {
    class MyViewHolder(private val binding: ItemRowUserBinding, private val name: String) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(user: DataItem){
            binding.apply {
                val firstname = user.firstName
                val lastname = user.lastName
                tvItemName.text = "$firstname $lastname"
                tvEmail.text = user.email
                Glide.with(itemView.context)
                    .load(user.avatar)
                    .into(ivAvatar)

                itemView.setOnClickListener {
                    val selectedUserName = "${user.firstName} ${user.lastName}"
                    val intent = Intent(itemView.context, SecondScreenActivity::class.java)
                    intent.putExtra(SecondScreenActivity.SELECTED_USER_NAME, selectedUserName)
                    intent.putExtra(SecondScreenActivity.NAME, name)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding, name)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        if (user != null) {
            holder.bind(user)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}