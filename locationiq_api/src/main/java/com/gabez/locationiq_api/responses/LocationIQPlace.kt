package com.gabez.locationiq_api.responses

data class LocationIQPlace(
    val place_id: String,
    val licence: String,
    val osm_type: String,
    val lat: Double,
    val lon: Double,
    val display_name: String,
    val address: Address,
    val boundingbox: List<Float>
)