package com.trusov.news_feed.data.remote.source

import androidx.paging.PagingSource
import com.trusov.news_feed.domain.entity.News
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val pagingSource: NewsPagingSource
) : RemoteDataSource {
    override fun getNewsFeed(): PagingSource<Int, News> {
        return pagingSource
    }


}