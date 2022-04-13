package com.trusov.news_feed.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trusov.news_feed.domain.entity.News
import com.trusov.news_feed.domain.use_case.GetNewsFeedUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsFeedViewModel @Inject constructor(
    private val getNewsFeedUseCase: GetNewsFeedUseCase
) : ViewModel() {

    private val _news = MutableLiveData<List<News>>()
    var news: LiveData<List<News>> = _news

    fun getNewsFeed(){
        viewModelScope.launch {
            _news.postValue(getNewsFeedUseCase()!!)
        }
    }

}