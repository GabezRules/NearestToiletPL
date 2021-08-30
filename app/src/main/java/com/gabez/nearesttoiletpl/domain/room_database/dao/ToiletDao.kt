package com.gabez.nearesttoiletpl.domain.room_database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gabez.nearesttoiletpl.domain.entity.Toilet

interface ToiletDao {
    @Query("SELECT * FROM toilet")
    fun getAllToilets(): List<Toilet>

    @Query("SELECT * FROM toilet WHERE place_id = :toiletId")
    fun getToiletById(toiletId: String): List<Toilet>

    @Insert
    fun insertToilet(toilet: Toilet)

    @Delete
    fun deleteToilet(toilet: Toilet)

    @Query("UPDATE toilet SET rate = :newRate WHERE place_id = :toiletId")
    fun updateToiletRate(toiletId: String, newRate: Float)

    @Query("DELETE FROM toilet")
    fun deleteAllToilets()
}