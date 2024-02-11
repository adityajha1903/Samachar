package com.adiandrodev.samachar.ui

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adiandrodev.samachar.Injector
import com.adiandrodev.samachar.R
import com.adiandrodev.samachar.databinding.ActivityMainBinding
import com.adiandrodev.samachar.ui.MainViewModel.Companion.CATEGORY_BUSINESS
import com.adiandrodev.samachar.ui.MainViewModel.Companion.CATEGORY_ENTERTAINMENT
import com.adiandrodev.samachar.ui.MainViewModel.Companion.CATEGORY_GENERAL
import com.adiandrodev.samachar.ui.MainViewModel.Companion.CATEGORY_HEALTH
import com.adiandrodev.samachar.ui.MainViewModel.Companion.CATEGORY_SCIENCE
import com.adiandrodev.samachar.ui.MainViewModel.Companion.CATEGORY_SPORTS
import com.adiandrodev.samachar.ui.MainViewModel.Companion.CATEGORY_TECHNOLOGY
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.kwabenaberko.newsapilib.models.Article
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel
    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    private var activeArticleList: ArrayList<Article>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolBar)

        (application as Injector).createMainSubComponent().inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        setArticleRecyclerView()

        setCheckedRadioBtnOnFirstRun()

        viewModel.activeArticleList.observe(this) {
            (binding.articleRecyclerView.adapter as ArticleRecyclerAdapter).articleListChanged(it?: ArrayList())
            if (!it.isNullOrEmpty()) {
                binding.warningTextView.visibility = View.GONE
            } else {
                binding.warningTextView.visibility = View.VISIBLE
            }
        }

        viewModel.progressVisibility.observe(this) {
            binding.viewBlocker.isClickable = it
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

        if (activeArticleList.isNullOrEmpty()) {
            getRemoteData()
        }

        setClickListeners()
    }

    private fun setClickListeners() {
        binding.articleCategoriesRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId) {
                binding.generalButton.id -> viewModel.activeCategory = CATEGORY_GENERAL
                binding.businessButton.id -> viewModel.activeCategory = CATEGORY_BUSINESS
                binding.entertainmentButton.id -> viewModel.activeCategory = CATEGORY_ENTERTAINMENT
                binding.healthButton.id -> viewModel.activeCategory = CATEGORY_HEALTH
                binding.scienceButton.id -> viewModel.activeCategory = CATEGORY_SCIENCE
                binding.sportsButton.id -> viewModel.activeCategory = CATEGORY_SPORTS
                binding.technologyButton.id -> viewModel.activeCategory = CATEGORY_TECHNOLOGY
            }
            getRemoteData()
        }
    }

    private fun showErrorSnackbar(errorMessage: String) {
        Snackbar.make(this, binding.root, errorMessage, Snackbar.LENGTH_SHORT)
            .setBackgroundTint(Color.RED)
            .setTextColor(Color.WHITE)
            .show()
    }

    private fun getRemoteData() {
        when (viewModel.activeCategory) {
            CATEGORY_GENERAL -> viewModel.getGeneralNews { showErrorSnackbar(it) }
            CATEGORY_BUSINESS -> viewModel.getBusinessNews { showErrorSnackbar(it) }
            CATEGORY_ENTERTAINMENT -> viewModel.getEntertainmentNews { showErrorSnackbar(it) }
            CATEGORY_HEALTH -> viewModel.getHealthNews { showErrorSnackbar(it) }
            CATEGORY_SCIENCE -> viewModel.getScienceNews { showErrorSnackbar(it) }
            CATEGORY_SPORTS -> viewModel.getSportsNews { showErrorSnackbar(it) }
            CATEGORY_TECHNOLOGY -> viewModel.getTechnologyNews { showErrorSnackbar(it) }
        }
    }

    private fun setCheckedRadioBtnOnFirstRun() {
        when (viewModel.activeCategory) {
            CATEGORY_GENERAL -> binding.generalButton.isChecked = true
            CATEGORY_BUSINESS -> binding.businessButton.isChecked = true
            CATEGORY_ENTERTAINMENT -> binding.entertainmentButton.isChecked = true
            CATEGORY_HEALTH -> binding.healthButton.isChecked = true
            CATEGORY_SCIENCE -> binding.scienceButton.isChecked = true
            CATEGORY_SPORTS -> binding.sportsButton.isChecked = true
            CATEGORY_TECHNOLOGY -> binding.technologyButton.isChecked = true
        }
    }

    private fun setArticleRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        binding.articleRecyclerView.layoutManager = layoutManager
        binding.articleRecyclerView.adapter = ArticleRecyclerAdapter({ imageView, imageUrl ->
            val url = imageUrl?:R.drawable.image_unavailable
            Glide.with(this)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.image_unavailable)
                .into(imageView)
        },{
            val intent = Intent(this, ArticleActivity::class.java)
            intent.putExtra(ArticleActivity.ARTICLE_URL, it)
            startActivity(intent)
        })
    }
}