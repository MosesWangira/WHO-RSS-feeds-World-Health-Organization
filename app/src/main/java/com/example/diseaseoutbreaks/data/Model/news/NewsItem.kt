package com.example.diseaseoutbreaks.data.Model.news

data class NewsItem(
    val author: String,
    val categories: List<Any>,
    val content: String,
    val description: String,
    val enclosure: Enclosure,
    val guid: String,
    val link: String,
    val pubDate: String,
    val thumbnail: String,
    val title: String
)