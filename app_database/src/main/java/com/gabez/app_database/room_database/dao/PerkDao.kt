package com.gabez.app_database.room_database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gabez.app_database.room_database.entity.DatabasePerk

@Dao
interface PerkDao {
    @Query("SELECT * FROM databaseperk")
    fun getAllPerks(): List<DatabasePerk>

    @Query("SELECT * FROM databaseperk WHERE id = :perkId")
    fun getPerkById(perkId: Int): List<DatabasePerk>

    @Insert
    fun insertPerk(databasePerk: DatabasePerk)

    @Delete
    fun deletePerk(databasePerk: DatabasePerk)

    @Query("DELETE FROM databaseperk")
    fun deleteAllPerks()

}