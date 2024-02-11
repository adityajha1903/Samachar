package com.adiandrodev.samachar.di

import com.adiandrodev.samachar.data.NewsRepositoryImpl
import com.adiandrodev.samachar.data.datasource.NewsCacheDataSource
import com.adiandrodev.samachar.data.datasource.NewsRemoteDataSource
import com.adiandrodev.samachar.data.datasourceimpl.NewsCacheDataSourceImpl
import com.adiandrodev.samachar.data.datasourceimpl.NewsRemoteDataSourceImpl
import com.adiandrodev.samachar.domain.NewsRepository
import com.adiandrodev.samachar.ui.MainViewModelFactory
import com.kwabenaberko.newsapilib.NewsApiClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainModule {

    @ActivityScope
    @Provides
    fun provideMainViewModelFactory(
        newsRepository: NewsRepository
    ): MainViewModelFactory {
        return MainViewModelFactory(
            newsRepository
        )
    }
}

@Module
class CacheDataModule {

    @Singleton
    @Provides
    fun provideNewsCacheDataSource(): NewsCacheDataSource {
        return NewsCacheDataSourceImpl()
    }
}

@Module
class RemoteDataModule(private val apiKey: String) {

    @Singleton
    @Provides
    fun provideApiKey(): String {
        return apiKey
    }

    @Singleton
    @Provides
    fun provideNewsApiClient(): NewsApiClient {
        return NewsApiClient(apiKey)
    }

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(
        newsApiClient: NewsApiClient
    ): NewsRemoteDataSource {
        return NewsRemoteDataSourceImpl(
            newsApiClient
        )
    }
}

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsRemoteDataSource: NewsRemoteDataSource,
        newsCacheDataSource: NewsCacheDataSource
    ): NewsRepository {
        return NewsRepositoryImpl(
            newsRemoteDataSource,
            newsCacheDataSource
        )
    }
}