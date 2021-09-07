package com.gabez.data_access.data.implementations.local_database

import android.content.Context
import androidx.room.Room
import com.gabez.app_database.room_database.NearestToiletAppDatabase
import com.gabez.app_database.room_database.entity.DatabasePerk
import com.gabez.app_database.room_database.entity.DatabaseShortPerkVotes
import com.gabez.app_database.room_database.entity.DatabaseToilet

class LocalDatasourceImpl(val context: Context):
    com.gabez.data_access.data.interfaces.local_database.LocalDatasource {

    private val db: NearestToiletAppDatabase by lazy {
        Room.databaseBuilder(
            context,
            NearestToiletAppDatabase::class.java, "nearest-toilet-db"
        ).build()
    }

    override fun getAllToilets(): List<DatabaseToilet> = db.toiletDao().getAllToilets()

    override fun getToiletById(toiletId: String): List<DatabaseToilet> = db.toiletDao().getToiletById(toiletId)

    override fun insertToilet(databaseToilet: DatabaseToilet) = db.toiletDao().insertToilet(databaseToilet)

    override fun deleteToilet(databaseToilet: DatabaseToilet) = db.toiletDao().deleteToilet(databaseToilet)

    override fun updateToiletRate(toiletId: String, newRate: Float) = db.toiletDao().updateToiletRate(toiletId, newRate)

    override fun deleteAllToilets() = db.toiletDao().deleteAllToilets()

    override fun getAllPerks(): List<DatabasePerk> = db.perkDao().getAllPerks()

    override fun getPerkById(perkId: Int): List<DatabasePerk> = db.perkDao().getPerkById(perkId)

    override fun insertPerk(databasePerk: DatabasePerk) = db.perkDao().insertPerk(databasePerk)

    override fun deletePerk(databasePerk: DatabasePerk) = db.perkDao().deletePerk(databasePerk)

    override fun deleteAllPerks() = db.perkDao().deleteAllPerks()

    override fun getAllShortPerksByToilet(toiletId: String): List<DatabaseShortPerkVotes> = db.shortPerkDao().getAllByToilet(toiletId)

    override fun deleteShortPerk(perkDatabase: DatabaseShortPerkVotes) = db.shortPerkDao().deleteShortPerk(perkDatabase)

    override fun insertShortPerk(perkDatabase: DatabaseShortPerkVotes) = db.shortPerkDao().insertShortPerk(perkDatabase)

    override fun updateVotesForShortPerk(perkId: Int, newVotes: Int) = db.shortPerkDao().updateVotesForShortPerk(perkId, newVotes)

    override fun deleteAllShortPerks() = db.shortPerkDao().deleteAllShortPerks()

    override fun deleteAllData() {
        db.toiletDao().deleteAllToilets()
        db.shortPerkDao().deleteAllShortPerks()
        db.perkDao().deleteAllPerks()
    }
}