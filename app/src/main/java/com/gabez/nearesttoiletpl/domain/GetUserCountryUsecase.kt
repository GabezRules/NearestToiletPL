package com.gabez.nearesttoiletpl.domain

import androidx.lifecycle.LiveData
import com.gabez.nearesttoiletpl.api.ApiResponse
import com.gabez.nearesttoiletpl.data.AppRepositoryImpl
import com.gabez.nearesttoiletpl.data.interfaces.AppRepository
import retrofit2.Response
import javax.inject.Inject

class GetUserCountryUsecase() {

    @Inject
    lateinit var appRepository: AppRepository

    suspend fun invoke(lat: String, lon: String): Response<String> = appRepository.getUserLocationCountry(lat, lon)
}