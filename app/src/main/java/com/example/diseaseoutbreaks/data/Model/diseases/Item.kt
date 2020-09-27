package com.example.diseaseoutbreaks.data.Model.diseases

import com.example.diseaseoutbreaks.data.Model.diseases.Enclosure

data class Item(
    val author: String,
    val categories: List<String>,
    val content: String,
    val description: String,
    val enclosure: Enclosure,
    val guid: String,
    val link: String,
    val pubDate: String,
    val thumbnail: String,
    val title: String
)