package com.adiandrodev.samachar.data.datasource

import com.kwabenaberko.newsapilib.models.Article

interface NewsCacheDataSource {

    fun getGeneralNews(): ArrayList<Article>?
    fun saveGeneralNews(articleList: ArrayList<Article>)

    fun getBusinessNews(): ArrayList<Article>?
    fun saveBusinessNews(articleList: ArrayList<Article>)

    fun getEntertainmentNews(): ArrayList<Article>?
    fun saveEntertainmentNews(articleList: ArrayList<Article>)

    fun getHealthNews(): ArrayList<Article>?
    fun saveHealthNews(articleList: ArrayList<Article>)

    fun getScienceNews(): ArrayList<Article>?
    fun saveScienceNews(articleList: ArrayList<Article>)

    suspend fun getSportsNews(): ArrayList<Article>?
    fun saveSportsNews(articleList: ArrayList<Article>)

    fun getTechnologyNews(): ArrayList<Article>?
    fun saveTechnologyNews(articleList: ArrayList<Article>)

}