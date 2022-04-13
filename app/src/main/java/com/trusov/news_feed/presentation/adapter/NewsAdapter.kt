package com.trusov.news_feed.presentation.adapter

import android.app.Application
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.trusov.news_feed.R
import com.trusov.news_feed.domain.entity.News
import javax.inject.Inject

class NewsAdapter @Inject constructor(
    private val application: Application
) : ListAdapter<News, NewsViewHolder>(NewsDiffCallback()) {

    var onNewsItemClickListener: ((News) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = currentList[position]
        with(holder.binding) {
            tvTitle.text = news.title ?: ""
            tvCreated.text = news.created ?: ""
            tvDescription.text = news.description ?: ""
            Log.d("NewsAdapter", news.imageUrl ?: "imageUrl = null")
            Glide.with(application)
                .load(news.imageUrl)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        ivImage.isGone = true
                        Log.d("NewsAdapter", e?.message ?: "error")
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }
                })
                .into(ivImage)

            root.setOnClickListener {
                onNewsItemClickListener?.invoke(news)
            }
        }
    }
}