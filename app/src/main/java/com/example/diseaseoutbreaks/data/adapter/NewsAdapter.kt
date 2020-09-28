package com.example.diseaseoutbreaks.data.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.diseaseoutbreaks.R
import com.example.diseaseoutbreaks.data.Model.news.NewsDataClass
import com.example.diseaseoutbreaks.data.Model.news.NewsItem
import kotlinx.android.synthetic.main.list_item_image_right.view.*

class NewsAdapter() :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var items =
        NewsDataClass(ArrayList())

    fun setDataList(items: List<NewsItem>) {
        this.items.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NewsViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_image_right,
            parent,
            false
        )
    )


    override fun getItemCount() = items.items.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        val news = items.items[position]

        holder.view.apply {
            titleRight.text = news.title
            publicationDateRight.text = "Publication Date : ${news.pubDate}"
            descriptionRight.text = news.description
        }

        Glide
            .with(holder.view.context)
            .load(R.drawable.news_paper_place_holder)
            .centerCrop()
            .placeholder(R.drawable.news_paper_place_holder)
            .into(holder.view.imageRight)
    }

    class NewsViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}