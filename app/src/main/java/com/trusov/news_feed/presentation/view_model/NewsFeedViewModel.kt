package com.trusov.news_feed.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trusov.news_feed.domain.use_case.GetNewsFeedUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsFeedViewModel @Inject constructor(
    private val getNewsFeedUseCase: GetNewsFeedUseCase
) : ViewModel() {

    fun getNewsFeed(){
        viewModelScope.launch {
            getNewsFeedUseCase()
        }
    }

}