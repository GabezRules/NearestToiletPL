package com.gabez.nearesttoiletpl.domain.entity

data class Toilet(
    val placeId: String,
    val lat: Double,
    val lon: Double,
    val displayName: String,
    val neighbourhood: String,
    val city: String,
    val postcode: String,
    val road: String,
    val features: List<String> = ArrayList(),
    val rate: Float = 0f,
    val rateCount: Int = 0,
    var perkIds: List<Int> = ArrayList()
)