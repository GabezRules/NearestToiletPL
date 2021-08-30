package com.gabez.app_database.room_database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gabez.app_database.room_database.entity.ShortPerkVotes

@Dao
interface ShortPerkDao {
    @Query("SELECT * FROM shortperkvotes WHERE toilet_id = :toiletId")
    fun getAllByToilet(toiletId: String): List<ShortPerkVotes>

    @Delete
    fun deleteShortPerk(perk: ShortPerkVotes)

    @Insert
    fun insertShortPerk(perk: ShortPerkVotes)

    @Query("UPDATE shortperkvotes SET votes = :newVotes WHERE id = :perkId")
    fun updateVotesForShortPerk(perkId: Int, newVotes: Int)

    @Query("DELETE FROM shortperkvotes")
    fun deleteAllShortPerks()
}