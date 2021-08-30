package com.gabez.nearesttoiletpl.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Toilet(
    @PrimaryKey
    @ColumnInfo(name = "place_id")
    val placeId: String,

    val lat: Double,

    val lon: Double,

    @ColumnInfo(name = "display_name")
    val displayName: String,

    val neighbourhood: String,

    val city: String,

    val postcode: String,

    val road: String,

    val features: List<String> = ArrayList(),

    val rate: Float = 0f,

    @ColumnInfo(name = "rate_count")
    val rateCount: Int = 0,

    @ColumnInfo(name = "perk_ids")
    var perkIds: List<Int> = ArrayList()
)