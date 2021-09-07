package com.gabez.data_access.data.interfaces.local_database

import com.gabez.app_database.room_database.entity.DatabasePerk
import com.gabez.app_database.room_database.entity.DatabaseShortPerkVotes
import com.gabez.app_database.room_database.entity.DatabaseToilet

interface LocalDatasource {
    fun getAllToilets(): List<DatabaseToilet>
    fun getToiletById(toiletId: String): List<DatabaseToilet>
    fun insertToilet(databaseToilet: DatabaseToilet)
    fun deleteToilet(databaseToilet: DatabaseToilet)
    fun updateToiletRate(toiletId: String, newRate: Float)
    fun deleteAllToilets()

    fun getAllPerks(): List<DatabasePerk>
    fun getPerkById(perkId: Int): List<DatabasePerk>
    fun insertPerk(databasePerk: DatabasePerk)
    fun deletePerk(databasePerk: DatabasePerk)
    fun deleteAllPerks()

    fun getAllShortPerksByToilet(toiletId: String): List<DatabaseShortPerkVotes>
    fun deleteShortPerk(perkDatabase: DatabaseShortPerkVotes)
    fun insertShortPerk(perkDatabase: DatabaseShortPerkVotes)
    fun updateVotesForShortPerk(perkId: Int, newVotes: Int)
    fun deleteAllShortPerks()

    fun deleteAllData()
}