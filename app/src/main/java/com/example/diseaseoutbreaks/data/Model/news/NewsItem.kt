package com.example.diseaseoutbreaks.data.Model.news

data class NewsItem(
    val feed: Feed,
    val items: List<Item>,
    val status: String
)