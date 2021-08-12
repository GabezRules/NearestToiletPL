package com.gabez.nearesttoiletpl.location

import android.util.Log

data class SearchBoundaries(val minLon: Double, val minLat: Double, val maxLon: Double, val maxLat: Double){
    override fun toString(): String {
        Log.e("API_ERROR", "$minLon%2C$minLat%2C$maxLat%2C$maxLat")
        return "$minLon%2C$minLat%2C$maxLat%2C$maxLat"
    }
}