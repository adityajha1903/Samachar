package com.adiandrodev.samachar.data.datasource

import com.kwabenaberko.newsapilib.models.Article

interface NewsRemoteDataSource {

    suspend fun getGeneralNewsFromRemote(cacheDataSource: NewsCacheDataSource, onSuccessListener: (articleList: ArrayList<Article>) -> Unit, onFailureListener: (errorMessage: String) -> Unit)
    suspend fun getBusinessNewsFromRemote(cacheDataSource: NewsCacheDataSource, onSuccessListener: (articleList: ArrayList<Article>) -> Unit, onFailureListener: (errorMessage: String) -> Unit)
    suspend fun getEntertainmentNewsFromRemote(cacheDataSource: NewsCacheDataSource, onSuccessListener: (articleList: ArrayList<Article>) -> Unit, onFailureListener: (errorMessage: String) -> Unit)
    suspend fun getHealthNewsFromRemote(cacheDataSource: NewsCacheDataSource, onSuccessListener: (articleList: ArrayList<Article>) -> Unit, onFailureListener: (errorMessage: String) -> Unit)
    suspend fun getScienceNewsFromRemote(cacheDataSource: NewsCacheDataSource, onSuccessListener: (articleList: ArrayList<Article>) -> Unit, onFailureListener: (errorMessage: String) -> Unit)
    suspend fun getSportsNewsFromRemote(cacheDataSource: NewsCacheDataSource, onSuccessListener: (articleList: ArrayList<Article>) -> Unit, onFailureListener: (errorMessage: String) -> Unit)
    suspend fun getTechnologyNewsFromRemote(cacheDataSource: NewsCacheDataSource, onSuccessListener: (articleList: ArrayList<Article>) -> Unit, onFailureListener: (errorMessage: String) -> Unit)

}