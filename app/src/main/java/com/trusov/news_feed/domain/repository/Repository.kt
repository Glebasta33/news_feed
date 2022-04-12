package com.trusov.news_feed.domain.repository

import com.trusov.news_feed.domain.entity.News

interface Repository {
    suspend fun getNewsFeed(): List<News>
    suspend fun getNewsDetailed(): News
}