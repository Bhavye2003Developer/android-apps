package com.example.nasafetcher.network.satSearch

data class Tle(
    val id: String,
    val type: String,
    val date: String,
    val line1: String,
    val line2: String,
    val name: String,
    val satelliteId: Int
)