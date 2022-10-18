package com.wooyj.alicorn.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wooyj.alicorn.data.model.ModelChatContent
import com.wooyj.alicorn.databinding.ItemChatContentMyBinding
import com.wooyj.alicorn.databinding.ItemChatContentOtherBinding


private const val TYPE_MY = 0
private const val TYPE_OTHER = 1


class ChatContentDiffCallback : DiffUtil.ItemCallback<ModelChatContent>() {
    override fun areItemsTheSame(oldItem: ModelChatContent, newItem: ModelChatContent): Boolean =
        oldItem.time == newItem.time

    override fun areContentsTheSame(oldItem: ModelChatContent, newItem: ModelChatContent): Boolean =
        oldItem == newItem
}

class ChatDetailAdapter() :
    ListAdapter<ModelChatContent, RecyclerView.ViewHolder>(ChatContentDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_MY -> {
                MyViewHolder.from(parent)
            }
            TYPE_OTHER -> {
                OtherViewHolder.from(parent)
            }
            else -> {
                MyViewHolder.from(parent)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is MyViewHolder->{
                holder.bind(getItem(position))
            }
            is OtherViewHolder->{
                holder.bind(getItem(position))
            }
        }
    }

    class MyViewHolder(val binding: ItemChatContentMyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ModelChatContent) {
            binding.item = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemChatContentMyBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }

    }

    class OtherViewHolder(val binding: ItemChatContentOtherBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ModelChatContent) {
            binding.item = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): OtherViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemChatContentOtherBinding.inflate(layoutInflater, parent, false)
                return OtherViewHolder(binding)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).isMy) {
            true -> TYPE_MY
            false -> TYPE_OTHER
        }
    }

}