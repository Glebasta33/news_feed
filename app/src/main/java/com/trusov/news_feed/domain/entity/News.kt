package com.trusov.news_feed.domain.entity

data class News(
    val author: String?,
    val title: String?,
    val description: String?,
    val imageUrl: String?,
    val content: String?
)
