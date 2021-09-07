package com.gabez.nearesttoiletpl.domain

import com.gabez.data_access.data.interfaces.AppRepository
import retrofit2.Response
import javax.inject.Inject

class GetUserCountryUsecase @Inject constructor(val appRepository: AppRepository) {

    suspend fun invoke(lat: String, lon: String): Response<String> = appRepository.getUserLocationCountry(lat, lon)
}