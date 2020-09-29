package com.example.diseaseoutbreaks.data.network

import com.example.diseaseoutbreaks.data.Model.news.NewsItem


data class NetworkNewsContainer(val disease: List<NewsDataClass>)

data class NewsDataClass(
    val author: String,
    val categories: List<Any>,
    val content: String,
    val description: String,
    val pubDate: String,
    val thumbnail: String,
    val title: String
)

/**
 * Convert Network results to database objects
 */
fun NetworkNewsContainer.asDomainModel(): List<NewsItem> {

    return disease.map {
        NewsItem(
            title = it.title,
            pubDate = it.pubDate,
            description = it.description,
            content = it.content,
            author = it.author,
            thumbnail = it.thumbnail,
            categories = it.categories
            )
    }
}