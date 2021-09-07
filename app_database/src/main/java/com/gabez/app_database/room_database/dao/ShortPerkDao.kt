package com.gabez.app_database.room_database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gabez.app_database.room_database.entity.DatabaseShortPerkVotes

@Dao
interface ShortPerkDao {
    @Query("SELECT * FROM databaseshortperkvotes WHERE toilet_id = :toiletId")
    fun getAllByToilet(toiletId: String): List<DatabaseShortPerkVotes>

    @Delete
    fun deleteShortPerk(perkDatabase: DatabaseShortPerkVotes)

    @Insert
    fun insertShortPerk(perkDatabase: DatabaseShortPerkVotes)

    @Query("UPDATE databaseshortperkvotes SET votes = :newVotes WHERE id = :perkId")
    fun updateVotesForShortPerk(perkId: Int, newVotes: Int)

    @Query("DELETE FROM databaseshortperkvotes")
    fun deleteAllShortPerks()
}