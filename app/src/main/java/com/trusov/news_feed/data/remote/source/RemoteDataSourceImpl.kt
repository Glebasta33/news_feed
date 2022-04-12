package com.trusov.news_feed.data.remote.source

import android.util.Log
import com.trusov.news_feed.data.remote.mapper.NewsMapper
import com.trusov.news_feed.data.remote.retrofit.ApiService
import com.trusov.news_feed.domain.entity.News
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: NewsMapper
) : RemoteDataSource {
    override suspend fun getNewsFeed(): List<News> {
        val responseDto = apiService.getNewsFeed()
        val news = responseDto.articles.map { mapper.mapArticleDtoToNewsEntity(it) }
        return news
    }

    override suspend fun getNewsDetailed(): News {
        TODO("Not yet implemented")
    }
}