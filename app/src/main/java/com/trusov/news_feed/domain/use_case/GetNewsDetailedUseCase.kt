package com.trusov.news_feed.domain.use_case

import com.trusov.news_feed.domain.entity.News
import com.trusov.news_feed.domain.repository.Repository
import javax.inject.Inject

class GetNewsDetailedUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun invoke() = repository.getNewsDetailed()
}