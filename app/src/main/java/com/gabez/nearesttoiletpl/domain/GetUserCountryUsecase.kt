package com.gabez.nearesttoiletpl.domain

import androidx.lifecycle.LiveData
import com.gabez.nearesttoiletpl.api.ApiResponse
import com.gabez.nearesttoiletpl.data.AppRepositoryImpl
import com.gabez.nearesttoiletpl.data.interfaces.AppRepository

class GetUserCountryUsecase constructor() {
    private val appRepository: AppRepository = AppRepositoryImpl.instance()

    suspend fun invoke(lat: String, lon: String) = appRepository.getUserLocationCountry(lat, lon)

    companion object{
        fun instance(): GetUserCountryUsecase = GetUserCountryUsecase()
    }
}