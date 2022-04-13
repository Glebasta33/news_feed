package com.trusov.news_feed.domain.repository

import androidx.paging.PagingSource
import com.trusov.news_feed.domain.entity.News

interface Repository {
    fun getNewsFeed(): PagingSource<Int, News>
}