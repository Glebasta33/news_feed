package com.trusov.news_feed.data.remote.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SourceDto(
    @SerializedName("id")
    @Expose
    val id: Any,
    @SerializedName("name")
    @Expose
    val name: String
)