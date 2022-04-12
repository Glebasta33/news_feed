package com.trusov.news_feed.domain.use_case

import com.trusov.news_feed.domain.repository.Repository
import javax.inject.Inject

class GetNewsFeedUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke() = repository.getNewsFeed()
}