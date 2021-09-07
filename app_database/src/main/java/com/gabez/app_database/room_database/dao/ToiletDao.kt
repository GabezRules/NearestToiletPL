package com.gabez.app_database.room_database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gabez.app_database.room_database.entity.DatabaseToilet

@Dao
interface ToiletDao {
    @Query("SELECT * FROM databasetoilet")
    fun getAllToilets(): List<DatabaseToilet>

    @Query("SELECT * FROM databasetoilet WHERE place_id = :toiletId")
    fun getToiletById(toiletId: String): List<DatabaseToilet>

    @Insert
    fun insertToilet(databaseToilet: DatabaseToilet)

    @Delete
    fun deleteToilet(databaseToilet: DatabaseToilet)

    @Query("UPDATE databasetoilet SET rate = :newRate WHERE place_id = :toiletId")
    fun updateToiletRate(toiletId: String, newRate: Float)

    @Query("DELETE FROM databasetoilet")
    fun deleteAllToilets()
}