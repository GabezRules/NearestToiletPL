package com.gabez.data_access

data class SearchBoundaries(val minLon: Double, val minLat: Double, val maxLon: Double, val maxLat: Double){
    override fun toString(): String {
        return "$minLon,$minLat,$maxLat,$maxLat"
    }
}