package com.adiandrodev.samachar.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.adiandrodev.samachar.databinding.ArticleRecyclerViewHolderBinding
import com.kwabenaberko.newsapilib.models.Article

class ArticleRecyclerAdapter(
    private val setImage: (imageView: ImageView, imageUrl: String?) -> Unit,
    private val clickListener: (url: String) -> Unit
): RecyclerView.Adapter<ArticleRecyclerAdapter.ViewHolder>() {

    private val articleList: ArrayList<Article> = ArrayList()

    class ViewHolder(binding: ArticleRecyclerViewHolderBinding): RecyclerView.ViewHolder(binding.root) {
        val card = binding.root
        val image = binding.articleImageView
        val title = binding.articleTitleTextView
        val author = binding.articleAuthorTextView
        val description = binding.articleDescriptionTextView
        val source = binding.articleSourceTextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ArticleRecyclerViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = articleList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articleList[position]

        setImage.invoke(holder.image, article.urlToImage)

        if (article.title.isNullOrEmpty()) {
            holder.title.visibility = View.GONE
        } else {
            holder.title.visibility = View.VISIBLE
            holder.title.text = article.title
        }

        if (article.source.name.isNullOrEmpty()) {
            holder.source.visibility = View.GONE
        } else {
            holder.source.visibility = View.VISIBLE
            holder.source.text = article.source.name
        }

        if (article.author.isNullOrEmpty()) {
            holder.author.visibility = View.GONE
        } else {
            holder.author.visibility = View.VISIBLE
            holder.author.text = article.author
        }

        if (article.description.isNullOrEmpty()) {
            holder.description.visibility = View.GONE
        } else {
            holder.description.visibility = View.VISIBLE
            holder.description.text = article.description
        }

        holder.card.setOnClickListener {
            clickListener.invoke(article.url)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun articleListChanged(newArticleList: ArrayList<Article>) {
        articleList.clear()
        newArticleList.forEachIndexed { index, article ->
            articleList.add(index, article)
        }
        notifyDataSetChanged()
    }
}