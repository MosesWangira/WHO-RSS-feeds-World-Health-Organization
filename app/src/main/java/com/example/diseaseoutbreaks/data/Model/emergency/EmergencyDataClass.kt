package com.example.diseaseoutbreaks.data.Model.emergency

data class EmergencyDataClass(
    val feed: Feed,
    val items: List<EmergencyItem>,
    val status: String
)