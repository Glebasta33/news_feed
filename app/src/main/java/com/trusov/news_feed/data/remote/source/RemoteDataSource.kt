package com.trusov.news_feed.data.remote.source

import androidx.paging.PagingSource
import com.trusov.news_feed.domain.entity.News

interface RemoteDataSource {
    fun getNewsFeed(): PagingSource<Int, News>
}