package com.adiandrodev.samachar.domain

import com.kwabenaberko.newsapilib.models.Article

interface NewsRepository {

    suspend fun getGeneralNews(onSuccessListener: (articleList: ArrayList<Article>) -> Unit, onFailureListener: (errorMessage: String) -> Unit)
    suspend fun getBusinessNews(onSuccessListener: (articleList: ArrayList<Article>) -> Unit, onFailureListener: (errorMessage: String) -> Unit)
    suspend fun getEntertainmentNews(onSuccessListener: (articleList: ArrayList<Article>) -> Unit, onFailureListener: (errorMessage: String) -> Unit)
    suspend fun getHealthNews(onSuccessListener: (articleList: ArrayList<Article>) -> Unit, onFailureListener: (errorMessage: String) -> Unit)
    suspend fun getScienceNews(onSuccessListener: (articleList: ArrayList<Article>) -> Unit, onFailureListener: (errorMessage: String) -> Unit)
    suspend fun getSportsNews(onSuccessListener: (articleList: ArrayList<Article>) -> Unit, onFailureListener: (errorMessage: String) -> Unit)
    suspend fun getTechnologyNews(onSuccessListener: (articleList: ArrayList<Article>) -> Unit, onFailureListener: (errorMessage: String) -> Unit)

}