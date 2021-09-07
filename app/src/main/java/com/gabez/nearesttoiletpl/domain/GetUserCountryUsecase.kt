package com.gabez.nearesttoiletpl.domain

import com.gabez.data_access.data.interfaces.AppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserCountryUsecase @Inject constructor(val appRepository: AppRepository) {

    suspend fun invoke(lat: String, lon: String): Flow<String> = appRepository.getUserLocationCountry(lat, lon)
}