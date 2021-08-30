package com.gabez.nearesttoiletpl.domain.room_database

import android.content.Context
import com.gabez.nearesttoiletpl.domain.entity.Perk
import com.gabez.nearesttoiletpl.domain.entity.ShortPerkVotes
import com.gabez.nearesttoiletpl.domain.entity.Toilet

interface DatabaseGateway {
    fun getAllToilets(): List<Toilet>
    fun getToiletById(toiletId: String): List<Toilet>
    fun insertToilet(toilet: Toilet)
    fun deleteToilet(toilet: Toilet)
    fun updateToiletRate(toiletId: String, newRate: Float)
    fun deleteAllToilets()

    fun getAllPerks(): List<Perk>
    fun getPerkById(perkId: Int): List<Perk>
    fun insertPerk(perk: Perk)
    fun deletePerk(perk: Perk)
    fun deleteAllPerks()

    fun getAllShortPerksByToilet(toiletId: String): List<ShortPerkVotes>
    fun deleteShortPerk(perk: ShortPerkVotes)
    fun insertShortPerk(perk: ShortPerkVotes)
    fun updateVotesForShortPerk(perkId: Int, newVotes: Int)
    fun deleteAllShortPerks()

    fun deleteAllData()
}