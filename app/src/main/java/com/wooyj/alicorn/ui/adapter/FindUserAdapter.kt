package com.wooyj.alicorn.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.wooyj.alicorn.data.model.ModelUser
import com.wooyj.alicorn.databinding.ItemUserListBinding

class UserListClickListener(val clickListener: (data: ModelUser) -> Unit) {
    fun onClick(data: ModelUser) = clickListener(data)
}

class FindUserDiffCallback : DiffUtil.ItemCallback<ModelUser>() {
    override fun areItemsTheSame(oldItem: ModelUser, newItem: ModelUser): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ModelUser, newItem: ModelUser): Boolean =
        oldItem == newItem
}

class FindUserAdapter(private val clickListener: UserListClickListener) :
    ListAdapter<ModelUser, FindUserAdapter.ViewHolder>(FindUserDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class ViewHolder private constructor(val binding: ItemUserListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ModelUser, clickListener: UserListClickListener) {
            binding.apply {
                this.item = item
                this.clickListener = clickListener
                executePendingBindings()
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemUserListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}