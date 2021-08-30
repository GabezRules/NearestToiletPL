package com.gabez.app_database.room_database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Perk(
    @PrimaryKey val id: Int,

    @ColumnInfo(name = "name_pl")
    val namePl: String,

    @ColumnInfo(name = "name_eng")
    val nameEng: String,

    @ColumnInfo(name = "icon_url")
    val iconUrl: String
)