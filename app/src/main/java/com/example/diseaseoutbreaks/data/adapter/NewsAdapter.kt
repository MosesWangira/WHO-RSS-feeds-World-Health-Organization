package com.example.diseaseoutbreaks.data.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diseaseoutbreaks.R
import com.example.diseaseoutbreaks.data.Model.news.NewsDataClass
import com.example.diseaseoutbreaks.data.Model.news.NewsItem
import kotlinx.android.synthetic.main.list_item_news.view.*

class NewsAdapter() :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var items =
        NewsDataClass(ArrayList())

    fun setDataList(items: List<NewsItem>) {
        this.items.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NewsViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_news,
            parent,
            false
        )
    )


    override fun getItemCount() = items.items.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        val news = items.items[position]

        holder.view.newsTitle.text = news.title
        holder.view.newsPublicationDate.text = "Publication Date : ${news.pubDate}"
        holder.view.newsDescription.text = news.description
    }

    class NewsViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}