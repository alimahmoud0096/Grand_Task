package com.ali.grandtask.presentation.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ali.grandtask.databinding.RawItemBinding
import com.ali.grandtask.domain.models.RedditItem


class NewsAdapter(var redditItemClickListener: RedditItemClickListener) : ListAdapter<RedditItem, NewsAdapter.ItemViewholder>(
    DiffCallback()
) {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            RawItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ,redditItemClickListener)

    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemViewholder(
        val binding: RawItemBinding,
        val redditItemClickListener: RedditItemClickListener?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RedditItem) = with(itemView) {
            binding.redditItem = item
            binding.clickListner = redditItemClickListener
            binding.executePendingBindings()
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<RedditItem>() {

    override fun areItemsTheSame(oldItem: RedditItem, newItem: RedditItem): Boolean {
        return oldItem?.id == newItem?.id
    }

    override fun areContentsTheSame(oldItem: RedditItem, newItem: RedditItem): Boolean {
        return oldItem == newItem
    }
}

class RedditItemClickListener(val clickListener: (redditItem: RedditItem) -> Unit) {
    fun onClick(redditItem: RedditItem) = clickListener(redditItem)
}