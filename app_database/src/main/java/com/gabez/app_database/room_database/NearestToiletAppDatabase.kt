package com.gabez.app_database.room_database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gabez.app_database.room_database.converters.Converters
import com.gabez.app_database.room_database.dao.PerkDao
import com.gabez.app_database.room_database.dao.ShortPerkDao
import com.gabez.app_database.room_database.dao.ToiletDao
import com.gabez.app_database.room_database.entity.DatabasePerk
import com.gabez.app_database.room_database.entity.DatabaseShortPerkVotes
import com.gabez.app_database.room_database.entity.DatabaseToilet

@Database(entities = arrayOf(DatabasePerk::class, DatabaseShortPerkVotes::class, DatabaseToilet::class), version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NearestToiletAppDatabase: RoomDatabase() {
    abstract fun perkDao(): PerkDao
    abstract fun shortPerkDao(): ShortPerkDao
    abstract fun toiletDao(): ToiletDao
}