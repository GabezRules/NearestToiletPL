package com.gabez.nearesttoiletpl.domain.room_database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gabez.nearesttoiletpl.domain.entity.Perk
import com.gabez.nearesttoiletpl.domain.entity.ShortPerkVotes
import com.gabez.nearesttoiletpl.domain.entity.Toilet
import com.gabez.nearesttoiletpl.domain.room_database.dao.PerkDao
import com.gabez.nearesttoiletpl.domain.room_database.dao.ShortPerkDao
import com.gabez.nearesttoiletpl.domain.room_database.dao.ToiletDao

@Database(entities = arrayOf(Perk::class, ShortPerkVotes::class, Toilet::class), version = 1)
abstract class NearestToiletAppDatabase: RoomDatabase() {
    abstract fun perkDao(): PerkDao
    abstract fun shortPerkDao(): ShortPerkDao
    abstract fun toiletDao(): ToiletDao
}