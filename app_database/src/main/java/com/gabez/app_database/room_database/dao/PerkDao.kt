package com.gabez.app_database.room_database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gabez.app_database.room_database.entity.Perk

@Dao
interface PerkDao {
    @Query("SELECT * FROM perk")
    fun getAllPerks(): List<Perk>

    @Query("SELECT * FROM perk WHERE id = :perkId")
    fun getPerkById(perkId: Int): List<Perk>

    @Insert
    fun insertPerk(perk: Perk)

    @Delete
    fun deletePerk(perk: Perk)

    @Query("DELETE FROM perk")
    fun deleteAllPerks()

}