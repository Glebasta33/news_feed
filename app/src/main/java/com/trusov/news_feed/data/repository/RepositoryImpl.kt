package com.trusov.news_feed.data.repository

import com.trusov.news_feed.data.remote.source.RemoteDataSource
import com.trusov.news_feed.domain.entity.News
import com.trusov.news_feed.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : Repository {
    override suspend fun getNewsFeed(): List<News> {
        return remoteDataSource.getNewsFeed()
    }

    override suspend fun getNewsDetailed(): News {
        return remoteDataSource.getNewsDetailed()
    }
}