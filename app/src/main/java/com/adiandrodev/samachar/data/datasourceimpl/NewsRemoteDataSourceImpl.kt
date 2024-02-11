package com.adiandrodev.samachar.data.datasourceimpl

import com.adiandrodev.samachar.data.datasource.NewsCacheDataSource
import com.adiandrodev.samachar.data.datasource.NewsRemoteDataSource
import com.kwabenaberko.newsapilib.NewsApiClient
import com.kwabenaberko.newsapilib.models.Article
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest
import com.kwabenaberko.newsapilib.models.response.ArticleResponse

class NewsRemoteDataSourceImpl(
    private val newsApiClient: NewsApiClient
): NewsRemoteDataSource {

    override suspend fun getGeneralNewsFromRemote(
        cacheDataSource: NewsCacheDataSource,
        onSuccessListener: (articleList: ArrayList<Article>) -> Unit,
        onFailureListener: (errorMessage: String) -> Unit
    ) {
        getNews(CATEGORY_GENERAL, cacheDataSource, onSuccessListener, onFailureListener)
    }

    override suspend fun getBusinessNewsFromRemote(
        cacheDataSource: NewsCacheDataSource,
        onSuccessListener: (articleList: ArrayList<Article>) -> Unit,
        onFailureListener: (errorMessage: String) -> Unit
    ) {
        getNews(CATEGORY_BUSINESS, cacheDataSource, onSuccessListener, onFailureListener)
    }

    override suspend fun getEntertainmentNewsFromRemote(
        cacheDataSource: NewsCacheDataSource,
        onSuccessListener: (articleList: ArrayList<Article>) -> Unit,
        onFailureListener: (errorMessage: String) -> Unit
    ) {
        getNews(CATEGORY_ENTERTAINMENT, cacheDataSource, onSuccessListener, onFailureListener)
    }

    override suspend fun getHealthNewsFromRemote(
        cacheDataSource: NewsCacheDataSource,
        onSuccessListener: (articleList: ArrayList<Article>) -> Unit,
        onFailureListener: (errorMessage: String) -> Unit
    ) {
        getNews(CATEGORY_HEALTH, cacheDataSource, onSuccessListener, onFailureListener)
    }

    override suspend fun getScienceNewsFromRemote(
        cacheDataSource: NewsCacheDataSource,
        onSuccessListener: (articleList: ArrayList<Article>) -> Unit,
        onFailureListener: (errorMessage: String) -> Unit
    ) {
        getNews(CATEGORY_SCIENCE, cacheDataSource, onSuccessListener, onFailureListener)
    }

    override suspend fun getSportsNewsFromRemote(
        cacheDataSource: NewsCacheDataSource,
        onSuccessListener: (articleList: ArrayList<Article>) -> Unit,
        onFailureListener: (errorMessage: String) -> Unit
    ) {
        getNews(CATEGORY_SPORTS, cacheDataSource, onSuccessListener, onFailureListener)
    }

    override suspend fun getTechnologyNewsFromRemote(
        cacheDataSource: NewsCacheDataSource,
        onSuccessListener: (articleList: ArrayList<Article>) -> Unit,
        onFailureListener: (errorMessage: String) -> Unit
    ) {
        getNews(CATEGORY_TECHNOLOGY, cacheDataSource, onSuccessListener, onFailureListener)
    }

    private fun getNews(category: String, cacheDataSource: NewsCacheDataSource, onSuccessListener: (articleList: ArrayList<Article>) -> Unit, onFailureListener: (errorMessage: String) -> Unit) {
        newsApiClient.getTopHeadlines(
            TopHeadlinesRequest.Builder()
                .language("en")
                .category(category)
                .build(),
            object: NewsApiClient.ArticlesResponseCallback {
                override fun onSuccess(response: ArticleResponse?) {
                    val articleList = response?.articles?.let { ArrayList(it) } ?: ArrayList()
                    onSuccessListener.invoke(articleList)
                    saveArticleInCacheDataSource(category, articleList, cacheDataSource)
                }
                override fun onFailure(throwable: Throwable?) {
                    onFailureListener.invoke(throwable?.message?: CUSTOM_ERROR_MESSAGE)
                }
            }
        )
    }

    private fun saveArticleInCacheDataSource(category: String, articleList: ArrayList<Article>, cacheDataSource: NewsCacheDataSource) {
        when(category) {
            CATEGORY_GENERAL -> cacheDataSource.saveGeneralNews(articleList)
            CATEGORY_BUSINESS -> cacheDataSource.saveBusinessNews(articleList)
            CATEGORY_ENTERTAINMENT -> cacheDataSource.saveEntertainmentNews(articleList)
            CATEGORY_HEALTH -> cacheDataSource.saveHealthNews(articleList)
            CATEGORY_SCIENCE -> cacheDataSource.saveScienceNews(articleList)
            CATEGORY_SPORTS -> cacheDataSource.saveSportsNews(articleList)
            CATEGORY_TECHNOLOGY -> cacheDataSource.saveTechnologyNews(articleList)
        }
    }

    companion object {
        private const val CATEGORY_GENERAL = "general"
        private const val CATEGORY_BUSINESS = "business"
        private const val CATEGORY_ENTERTAINMENT = "entertainment"
        private const val CATEGORY_HEALTH = "health"
        private const val CATEGORY_SCIENCE = "science"
        private const val CATEGORY_SPORTS = "sports"
        private const val CATEGORY_TECHNOLOGY = "technology"
        private const val CUSTOM_ERROR_MESSAGE = "Something went wrong!!"
    }

}