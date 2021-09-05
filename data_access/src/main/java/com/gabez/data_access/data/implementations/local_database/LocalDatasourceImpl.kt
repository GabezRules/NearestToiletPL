package com.gabez.data_access.data.implementations.local_database

import android.content.Context
import androidx.room.Room
import com.gabez.app_database.room_database.NearestToiletAppDatabase
import com.gabez.app_database.room_database.entity.Perk
import com.gabez.app_database.room_database.entity.ShortPerkVotes
import com.gabez.app_database.room_database.entity.Toilet

class LocalDatasourceImpl(val context: Context):
    com.gabez.data_access.data.interfaces.local_database.LocalDatasource {

    private val db: NearestToiletAppDatabase by lazy {
        Room.databaseBuilder(
            context,
            NearestToiletAppDatabase::class.java, "nearest-toilet-db"
        ).build()
    }

    override fun getAllToilets(): List<Toilet> = db.toiletDao().getAllToilets()

    override fun getToiletById(toiletId: String): List<Toilet> = db.toiletDao().getToiletById(toiletId)

    override fun insertToilet(toilet: Toilet) = db.toiletDao().insertToilet(toilet)

    override fun deleteToilet(toilet: Toilet) = db.toiletDao().deleteToilet(toilet)

    override fun updateToiletRate(toiletId: String, newRate: Float) = db.toiletDao().updateToiletRate(toiletId, newRate)

    override fun deleteAllToilets() = db.toiletDao().deleteAllToilets()

    override fun getAllPerks(): List<Perk> = db.perkDao().getAllPerks()

    override fun getPerkById(perkId: Int): List<Perk> = db.perkDao().getPerkById(perkId)

    override fun insertPerk(perk: Perk) = db.perkDao().insertPerk(perk)

    override fun deletePerk(perk: Perk) = db.perkDao().deletePerk(perk)

    override fun deleteAllPerks() = db.perkDao().deleteAllPerks()

    override fun getAllShortPerksByToilet(toiletId: String): List<ShortPerkVotes> = db.shortPerkDao().getAllByToilet(toiletId)

    override fun deleteShortPerk(perk: ShortPerkVotes) = db.shortPerkDao().deleteShortPerk(perk)

    override fun insertShortPerk(perk: ShortPerkVotes) = db.shortPerkDao().insertShortPerk(perk)

    override fun updateVotesForShortPerk(perkId: Int, newVotes: Int) = db.shortPerkDao().updateVotesForShortPerk(perkId, newVotes)

    override fun deleteAllShortPerks() = db.shortPerkDao().deleteAllShortPerks()

    override fun deleteAllData() {
        db.toiletDao().deleteAllToilets()
        db.shortPerkDao().deleteAllShortPerks()
        db.perkDao().deleteAllPerks()
    }
}