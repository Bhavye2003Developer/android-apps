package com.example.nasafetcher.network.satSearch

data class Satellite(
    val context: String,
    val id: String,
    val type: String,
    val algorithm: String,
    val geodetic: Geodetic,
    val parameters: Parameters,
    val tle: Tle,
    val vector: Vector
)