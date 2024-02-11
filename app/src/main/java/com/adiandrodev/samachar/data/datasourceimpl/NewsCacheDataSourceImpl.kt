package com.adiandrodev.samachar.data.datasourceimpl

import com.adiandrodev.samachar.data.datasource.NewsCacheDataSource
import com.kwabenaberko.newsapilib.models.Article

class NewsCacheDataSourceImpl: NewsCacheDataSource {

    private var generalArticleList: ArrayList<Article>? = null
    override fun getGeneralNews(): ArrayList<Article>? {
        return generalArticleList
    }
    override fun saveGeneralNews(articleList: ArrayList<Article>) {
        if (articleList.isNotEmpty()) {
            generalArticleList = articleList
        }
    }

    private var businessArticleList: ArrayList<Article>? = null
    override fun getBusinessNews(): ArrayList<Article>? {
        return businessArticleList
    }
    override fun saveBusinessNews(articleList: ArrayList<Article>) {
        if (articleList.isNotEmpty()) {
            businessArticleList = articleList
        }
    }

    private var entertainmentArticleList: ArrayList<Article>? = null
    override fun getEntertainmentNews(): ArrayList<Article>? {
        return entertainmentArticleList
    }
    override fun saveEntertainmentNews(articleList: ArrayList<Article>) {
        if (articleList.isNotEmpty()) {
            entertainmentArticleList = articleList
        }
    }

    private var healthArticleList: ArrayList<Article>? = null
    override fun getHealthNews(): ArrayList<Article>? {
        return healthArticleList
    }
    override fun saveHealthNews(articleList: ArrayList<Article>) {
        if (articleList.isNotEmpty()) {
            healthArticleList = articleList
        }
    }

    private var scienceArticleList: ArrayList<Article>? = null
    override fun getScienceNews(): ArrayList<Article>? {
        return scienceArticleList
    }
    override fun saveScienceNews(articleList: ArrayList<Article>) {
        if (articleList.isNotEmpty()) {
            scienceArticleList = articleList
        }
    }

    private var sportsArticleList: ArrayList<Article>? = null
    override suspend fun getSportsNews(): ArrayList<Article>? {
        return sportsArticleList
    }
    override fun saveSportsNews(articleList: ArrayList<Article>) {
        if (articleList.isNotEmpty()) {
            sportsArticleList = articleList
        }
    }

    private var technologyArticleList: ArrayList<Article>? = null
    override fun getTechnologyNews(): ArrayList<Article>? {
        return technologyArticleList
    }
    override fun saveTechnologyNews(articleList: ArrayList<Article>) {
        if (articleList.isNotEmpty()) {
            technologyArticleList = articleList
        }
    }
}