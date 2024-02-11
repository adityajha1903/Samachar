package com.adiandrodev.samachar.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.adiandrodev.samachar.domain.NewsRepository
import com.kwabenaberko.newsapilib.models.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val newsRepository: NewsRepository
): ViewModel() {

    val progressVisibility = MutableLiveData(false)
    val activeArticleList = MutableLiveData(ArrayList<Article>())
    var activeCategory = CATEGORY_GENERAL

    private var generalNews = ArrayList<Article>()
    private var businessNews = ArrayList<Article>()
    private var entertainmentNews = ArrayList<Article>()
    private var healthNews = ArrayList<Article>()
    private var scienceNews = ArrayList<Article>()
    private var sportsNews = ArrayList<Article>()
    private var technologyNews = ArrayList<Article>()

    private fun setActiveCategoryAndArticleList(category: String, list: ArrayList<Article>) {
        activeCategory = category
        val newList = ArrayList<Article>()
        list.forEachIndexed { index, article ->
            newList.add(index, article)
        }
        activeArticleList.value = newList
    }

    fun getGeneralNews(onFailureListener: (errorMessage: String) -> Unit) {
        if (generalNews.isNotEmpty()) {
            setActiveCategoryAndArticleList(CATEGORY_GENERAL, generalNews)
            return
        }
        progressVisibility.value = true
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.getGeneralNews({ list ->
                viewModelScope.launch(Dispatchers.Main) {
                    generalNews = list
                    setActiveCategoryAndArticleList(CATEGORY_GENERAL, generalNews)
                    progressVisibility.value = false
                }
            }, onFailureListener)
        }
    }

    fun getBusinessNews(onFailureListener: (errorMessage: String) -> Unit) {
        if (businessNews.isNotEmpty()) {
            setActiveCategoryAndArticleList(CATEGORY_BUSINESS, businessNews)
            return
        }
        progressVisibility.value = true
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.getBusinessNews({ list ->
                viewModelScope.launch(Dispatchers.Main) {
                    businessNews = list
                    setActiveCategoryAndArticleList(CATEGORY_BUSINESS, businessNews)
                    progressVisibility.value = false
                }
            }, onFailureListener)
        }
    }

    fun getEntertainmentNews(onFailureListener: (errorMessage: String) -> Unit) {
        if (entertainmentNews.isNotEmpty()) {
            setActiveCategoryAndArticleList(CATEGORY_ENTERTAINMENT, entertainmentNews)
            return
        }
        progressVisibility.value = true
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.getEntertainmentNews({ list ->
                viewModelScope.launch(Dispatchers.Main) {
                    entertainmentNews = list
                    setActiveCategoryAndArticleList(CATEGORY_ENTERTAINMENT, entertainmentNews)
                    progressVisibility.value = false
                }
            }, onFailureListener)
        }
    }

    fun getHealthNews(onFailureListener: (errorMessage: String) -> Unit) {
        if (healthNews.isNotEmpty()) {
            setActiveCategoryAndArticleList(CATEGORY_HEALTH, healthNews)
            return
        }
        progressVisibility.value = true
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.getHealthNews({ list ->
                viewModelScope.launch(Dispatchers.Main) {
                    entertainmentNews = list
                    setActiveCategoryAndArticleList(CATEGORY_HEALTH, healthNews)
                    progressVisibility.value = false
                }
            }, onFailureListener)
        }
    }

    fun getScienceNews(onFailureListener: (errorMessage: String) -> Unit) {
        if (scienceNews.isNotEmpty()) {
            setActiveCategoryAndArticleList(CATEGORY_SCIENCE, scienceNews)
            return
        }
        progressVisibility.value = true
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.getHealthNews({ list ->
                viewModelScope.launch(Dispatchers.Main) {
                    scienceNews = list
                    setActiveCategoryAndArticleList(CATEGORY_SCIENCE, scienceNews)
                    progressVisibility.value = false
                }
            }, onFailureListener)
        }
    }

    fun getSportsNews(onFailureListener: (errorMessage: String) -> Unit) {
        if (sportsNews.isNotEmpty()) {
            setActiveCategoryAndArticleList(CATEGORY_SPORTS, sportsNews)
            return
        }
        progressVisibility.value = true
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.getSportsNews({ list ->
                viewModelScope.launch(Dispatchers.Main) {
                    sportsNews = list
                    setActiveCategoryAndArticleList(CATEGORY_SPORTS, sportsNews)
                    progressVisibility.value = false
                }
            }, onFailureListener)
        }
    }

    fun getTechnologyNews(onFailureListener: (errorMessage: String) -> Unit) {
        if (technologyNews.isNotEmpty()) {
            setActiveCategoryAndArticleList(CATEGORY_TECHNOLOGY, technologyNews)
            return
        }
        progressVisibility.value = true
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.getTechnologyNews({ list ->
                viewModelScope.launch(Dispatchers.Main) {
                    technologyNews = list
                    setActiveCategoryAndArticleList(CATEGORY_TECHNOLOGY, technologyNews)
                    progressVisibility.value = false
                }
            }, onFailureListener)
        }
    }

    companion object {
        const val CATEGORY_GENERAL = "general"
        const val CATEGORY_BUSINESS = "business"
        const val CATEGORY_ENTERTAINMENT = "entertainment"
        const val CATEGORY_HEALTH = "health"
        const val CATEGORY_SCIENCE = "science"
        const val CATEGORY_SPORTS = "sports"
        const val CATEGORY_TECHNOLOGY = "technology"
    }
}

class MainViewModelFactory(
    private val newsRepository: NewsRepository
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(newsRepository) as T
    }
}