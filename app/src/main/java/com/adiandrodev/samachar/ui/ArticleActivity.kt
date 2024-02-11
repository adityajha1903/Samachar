package com.adiandrodev.samachar.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.adiandrodev.samachar.databinding.ActivityArticleBinding

class ArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url = intent.getStringExtra(ARTICLE_URL)
        binding.webView.webViewClient = WebViewClient()
        url?.let {
            binding.webView.loadUrl(url)
        }
    }

    companion object {
        const val ARTICLE_URL = "article_url"
    }
}