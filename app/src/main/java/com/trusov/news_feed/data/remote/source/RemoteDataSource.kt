package com.trusov.news_feed.data.remote.source

import com.trusov.news_feed.domain.entity.News

interface RemoteDataSource {
    suspend fun getNewsFeed(): List<News>
    suspend fun getNewsDetailed(): News
}