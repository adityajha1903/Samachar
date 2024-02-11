package com.adiandrodev.samachar.data

import com.adiandrodev.samachar.data.datasource.NewsCacheDataSource
import com.adiandrodev.samachar.data.datasource.NewsRemoteDataSource
import com.adiandrodev.samachar.domain.NewsRepository
import com.kwabenaberko.newsapilib.models.Article

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsCacheDataSource: NewsCacheDataSource
): NewsRepository {

    override suspend fun getGeneralNews(
        onSuccessListener: (articleList: ArrayList<Article>) -> Unit,
        onFailureListener: (errorMessage: String) -> Unit
    ) {
        val articleList = newsCacheDataSource.getGeneralNews()
        if (!articleList.isNullOrEmpty()) {
            onSuccessListener.invoke(articleList)
        } else {
            newsRemoteDataSource.getGeneralNewsFromRemote(newsCacheDataSource, onSuccessListener, onFailureListener)
        }
    }

    override suspend fun getBusinessNews(
        onSuccessListener: (articleList: ArrayList<Article>) -> Unit,
        onFailureListener: (errorMessage: String) -> Unit
    ) {
        val articleList = newsCacheDataSource.getBusinessNews()
        if (!articleList.isNullOrEmpty()) {
            onSuccessListener.invoke(articleList)
        } else {
            newsRemoteDataSource.getBusinessNewsFromRemote(newsCacheDataSource, onSuccessListener, onFailureListener)
        }
    }

    override suspend fun getEntertainmentNews(
        onSuccessListener: (articleList: ArrayList<Article>) -> Unit,
        onFailureListener: (errorMessage: String) -> Unit
    ) {
        val articleList = newsCacheDataSource.getEntertainmentNews()
        if (!articleList.isNullOrEmpty()) {
            onSuccessListener.invoke(articleList)
        } else {
            newsRemoteDataSource.getEntertainmentNewsFromRemote(newsCacheDataSource, onSuccessListener, onFailureListener)
        }
    }

    override suspend fun getHealthNews(
        onSuccessListener: (articleList: ArrayList<Article>) -> Unit,
        onFailureListener: (errorMessage: String) -> Unit
    ) {
        val articleList = newsCacheDataSource.getHealthNews()
        if (!articleList.isNullOrEmpty()) {
            onSuccessListener.invoke(articleList)
        } else {
            newsRemoteDataSource.getHealthNewsFromRemote(newsCacheDataSource, onSuccessListener, onFailureListener)
        }
    }

    override suspend fun getScienceNews(
        onSuccessListener: (articleList: ArrayList<Article>) -> Unit,
        onFailureListener: (errorMessage: String) -> Unit
    ) {
        val articleList = newsCacheDataSource.getScienceNews()
        if (!articleList.isNullOrEmpty()) {
            onSuccessListener.invoke(articleList)
        } else {
            newsRemoteDataSource.getScienceNewsFromRemote(newsCacheDataSource, onSuccessListener, onFailureListener)
        }
    }

    override suspend fun getSportsNews(
        onSuccessListener: (articleList: ArrayList<Article>) -> Unit,
        onFailureListener: (errorMessage: String) -> Unit
    ) {
        val articleList = newsCacheDataSource.getSportsNews()
        if (!articleList.isNullOrEmpty()) {
            onSuccessListener.invoke(articleList)
        } else {
            newsRemoteDataSource.getSportsNewsFromRemote(newsCacheDataSource, onSuccessListener, onFailureListener)
        }
    }

    override suspend fun getTechnologyNews(
        onSuccessListener: (articleList: ArrayList<Article>) -> Unit,
        onFailureListener: (errorMessage: String) -> Unit
    ) {
        val articleList = newsCacheDataSource.getTechnologyNews()
        if (!articleList.isNullOrEmpty()) {
            onSuccessListener.invoke(articleList)
        } else {
            newsRemoteDataSource.getTechnologyNewsFromRemote(newsCacheDataSource, onSuccessListener, onFailureListener)
        }
    }
}