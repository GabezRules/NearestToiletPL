package com.gabez.nearesttoiletpl.domain

import com.gabez.data_access.Place
import com.gabez.data_access.data.interfaces.AppRepository
import com.gabez.data_access.SearchBoundaries
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNearbyToiletsUsecase @Inject constructor(val appRepository: AppRepository) {

    suspend fun invoke(bounds: SearchBoundaries): Flow<List<Place>> =
        appRepository.getNearbyToilets(bounds)
}