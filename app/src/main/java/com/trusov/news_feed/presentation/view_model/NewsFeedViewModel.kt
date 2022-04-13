package com.trusov.news_feed.presentation.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.trusov.news_feed.domain.entity.News
import com.trusov.news_feed.domain.use_case.GetNewsFeedUseCase
import kotlinx.coroutines.launch
import java.util.concurrent.Flow
import javax.inject.Inject

class NewsFeedViewModel @Inject constructor(
    private val getNewsFeedUseCase: GetNewsFeedUseCase
) : ViewModel() {

    val news = Pager(config = PagingConfig(pageSize = 20, prefetchDistance = 3),
        pagingSourceFactory = {getNewsFeedUseCase()}
        ).flow.cachedIn(viewModelScope)

}