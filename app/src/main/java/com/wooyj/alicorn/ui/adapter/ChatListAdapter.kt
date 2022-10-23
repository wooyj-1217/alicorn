package com.wooyj.alicorn.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.wooyj.alicorn.data.model.ModelChatList
import com.wooyj.alicorn.databinding.ItemChatListBinding

class ChatListClickListener(val clickListener: (data: ModelChatList) -> Unit) {
    fun onClick(data: ModelChatList) = clickListener(data)
}

class ChatListDiffCallBack : DiffUtil.ItemCallback<ModelChatList>() {
    override fun areItemsTheSame(oldItem: ModelChatList, newItem: ModelChatList): Boolean =
        oldItem.chatId == newItem.chatId

    override fun areContentsTheSame(oldItem: ModelChatList, newItem: ModelChatList): Boolean =
        oldItem == newItem
}

class ChatListAdapter(private val clickListener: ChatListClickListener) :
    ListAdapter<ModelChatList, ChatListAdapter.ViewHolder>(ChatListDiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class ViewHolder private constructor(val binding: ItemChatListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ModelChatList, clickListener: ChatListClickListener) {
            binding.apply {
                this.item = item
                this.clickListener = clickListener
                executePendingBindings()
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemChatListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}