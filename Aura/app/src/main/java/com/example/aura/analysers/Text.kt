package com.example.aura.analysers

import android.util.Log
import com.google.mlkit.nl.entityextraction.DateTimeEntity
import com.google.mlkit.nl.entityextraction.Entity
import com.google.mlkit.nl.entityextraction.EntityExtraction
import com.google.mlkit.nl.entityextraction.EntityExtractionParams
import com.google.mlkit.nl.entityextraction.EntityExtractor
import com.google.mlkit.nl.entityextraction.EntityExtractorOptions
import com.google.mlkit.nl.entityextraction.FlightNumberEntity
import com.google.mlkit.nl.entityextraction.MoneyEntity

class Text {

    private var isModelDownloaded: Boolean = false

    private val TAG = "analyse"

    private var entityExtractor: EntityExtractor = EntityExtraction.getClient(
        EntityExtractorOptions.Builder(EntityExtractorOptions.ENGLISH)
            .build()
    )

    init {
        entityExtractor
            .downloadModelIfNeeded()
            .addOnSuccessListener { _ ->
                /* Model downloading succeeded, you can call extraction API here. */
                isModelDownloaded = true
            }
            .addOnFailureListener { _ ->
                /* Model downloading failed. */
            }
    }

    fun analyse(
//        onSuccess: () -> Unit,
        onFailure: (err: String) -> Unit
    ) {
        Log.d(TAG, "Model downloaded: $isModelDownloaded")
        val params =
            EntityExtractionParams.Builder("I am Bhavye, a software engineer graduated in 2024 and have 3 years of experience")
                .build()
        entityExtractor
            .annotate(params)
            .addOnSuccessListener {
                // Annotation process was successful, you can parse the EntityAnnotations list here.
//                    onSuccess(it)

                for (entityAnnotation in it) {
                    val entities: List<Entity> = entityAnnotation.entities

                    Log.d(TAG, "Range: ${entityAnnotation.start} - ${entityAnnotation.end}")
                    for (entity in entities) {
                        when (entity) {
                            is DateTimeEntity -> {
                                Log.d(TAG, "Granularity: ${entity.dateTimeGranularity}")
                                Log.d(TAG, "TimeStamp: ${entity.timestampMillis}")
                            }

                            is FlightNumberEntity -> {
                                Log.d(TAG, "Airline Code: ${entity.airlineCode}")
                                Log.d(TAG, "Flight number: ${entity.flightNumber}")
                            }

                            is MoneyEntity -> {
                                Log.d(TAG, "Currency: ${entity.unnormalizedCurrency}")
                                Log.d(TAG, "Integer part: ${entity.integerPart}")
                                Log.d(TAG, "Fractional Part: ${entity.fractionalPart}")
                            }

                            else -> {
                                Log.d(TAG, "  $entity")
                            }
                        }
                    }
                }
            }
            .addOnFailureListener {
                // Check failure message here.
                onFailure(it.toString())
            }
    }

}