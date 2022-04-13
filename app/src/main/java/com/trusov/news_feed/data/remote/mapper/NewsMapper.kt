package com.trusov.news_feed.data.remote.mapper

import com.trusov.news_feed.data.remote.dto.ArticleDto
import com.trusov.news_feed.domain.entity.News
import javax.inject.Inject

class NewsMapper @Inject constructor() {
    fun mapArticleDtoToNewsEntity(dto: ArticleDto) = News(
        author = dto.author,
        title = dto.title,
        description = dto.description,
        imageUrl = dto.urlToImage,
        content = dto.content,
        created = dto.publishedAt?.replace("T", " ")?.replace("Z", " ")
    )
}