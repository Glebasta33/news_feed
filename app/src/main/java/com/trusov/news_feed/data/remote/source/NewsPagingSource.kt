package com.trusov.news_feed.data.remote.source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.trusov.news_feed.data.remote.mapper.NewsMapper
import com.trusov.news_feed.data.remote.retrofit.ApiService
import com.trusov.news_feed.domain.entity.News
import javax.inject.Inject

class NewsPagingSource @Inject constructor(
    private val service: ApiService,
    private val mapper: NewsMapper
) : PagingSource<Int, News>() {

    override fun getRefreshKey(state: PagingState<Int, News>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, News> {
        try {
            val pageNumber = params.key ?: INITIAL_PAGE_NUMBER
            val pageSize = params.loadSize.coerceAtMost(MAX_PAGE_SIZE)
            val response = service.getNewsFeed(
                page = pageNumber,
                pageSize = pageSize
            )
            Log.d("NewsPagingSource", "pageNumber: $pageNumber, pageSize: $pageSize")
            if (response.status == "ok") {
                val news = response.articles.map { mapper.mapArticleDtoToNewsEntity(it) }
                val nextPageNumber = if (news.isEmpty()) null else pageNumber + 1
                val prevPageNumber = if (pageNumber > 1) pageNumber -1 else null
                Log.d("NewsPagingSource", "nextPageNumber: $nextPageNumber")
                return LoadResult.Page(news, prevPageNumber, nextPageNumber)
            } else {
                return LoadResult.Error(RuntimeException("Response is wrong"))
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    companion object {

        const val INITIAL_PAGE_NUMBER = 1
        const val MAX_PAGE_SIZE = 50
    }
}