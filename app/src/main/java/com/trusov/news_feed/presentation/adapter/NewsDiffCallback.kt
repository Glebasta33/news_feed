package com.trusov.news_feed.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.trusov.news_feed.domain.entity.News

class NewsDiffCallback : DiffUtil.ItemCallback<News>() {
    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem == newItem
    }
}