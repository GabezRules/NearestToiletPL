package com.gabez.nearesttoiletpl.api

data class GetRequest(val lat: Long, val lon: Long, val format: String = "json")
