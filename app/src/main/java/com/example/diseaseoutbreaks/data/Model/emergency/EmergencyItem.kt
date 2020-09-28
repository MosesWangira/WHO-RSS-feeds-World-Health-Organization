package com.example.diseaseoutbreaks.data.Model.emergency

data class EmergencyItem(
    val author: String,
    val categories: List<String>,
    val content: String,
    val description: String,
    val guid: String,
    val link: String,
    val pubDate: String,
    val thumbnail: String,
    val title: String
)