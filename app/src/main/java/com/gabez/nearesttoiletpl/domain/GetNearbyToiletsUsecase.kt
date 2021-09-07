package com.gabez.nearesttoiletpl.domain

import com.gabez.data_access.data.interfaces.AppRepository
import com.gabez.data_access.SearchBoundaries
import retrofit2.Response
import javax.inject.Inject

class GetNearbyToiletsUsecase @Inject constructor(val appRepository: AppRepository) {

    suspend fun invoke(bounds: SearchBoundaries): Response<String> =
        appRepository.getNearbyToilets(bounds)
}