package com.gabez.app_database.room_database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ShortPerkVotes(
    @PrimaryKey val id: Int,

    @ColumnInfo(name = "toilet_id")
    val toiletId: String,

    @ColumnInfo(name = "perk_id")
    val perkId: String,

    val votes: Int
)