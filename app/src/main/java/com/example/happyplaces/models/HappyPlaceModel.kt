package com.example.happyplaces.models

data class HappyPlaceModel(
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val date: String,
    val location: String,
    val latitude: Double,
    val longitude: Double,
)