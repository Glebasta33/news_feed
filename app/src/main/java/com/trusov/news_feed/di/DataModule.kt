package com.trusov.news_feed.di

import com.trusov.news_feed.data.remote.retrofit.ApiFactory
import com.trusov.news_feed.data.remote.retrofit.ApiService
import com.trusov.news_feed.data.remote.source.RemoteDataSource
import com.trusov.news_feed.data.remote.source.RemoteDataSourceImpl
import com.trusov.news_feed.data.repository.RepositoryImpl
import com.trusov.news_feed.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindRepository(impl: RepositoryImpl): Repository

    @Binds
    fun bindRemoteDataSource(impl: RemoteDataSourceImpl): RemoteDataSource

    companion object {
        @ApplicationScope
        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.service
        }
    }

}