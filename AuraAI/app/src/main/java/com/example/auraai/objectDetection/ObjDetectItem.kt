package com.example.auraai.objectDetection

data class ObjDetectItem(
    val box: Box,
    val label: String,
    val score: Double
)