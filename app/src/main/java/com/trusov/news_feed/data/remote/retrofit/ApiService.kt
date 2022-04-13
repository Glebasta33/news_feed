package com.trusov.news_feed.data.remote.retrofit

import com.trusov.news_feed.data.remote.dto.ResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines")
    suspend fun getNewsFeed(
        @Query(COUNTRY_PARAM) country: String = "us",
        @Query(API_KEY_PARAM) apiKey: String = "2f1645d199a340ee8456005e8e076435",
        @Query(PAGE_PARAM) page: Int,
        @Query(PAGE_SIZE_PARAM) pageSize: Int
    ): ResponseDto

    companion object {
        private const val COUNTRY_PARAM = "country"
        private const val API_KEY_PARAM = "apiKey"
        private const val PAGE_PARAM = "page"
        private const val PAGE_SIZE_PARAM = "page"
    }
}