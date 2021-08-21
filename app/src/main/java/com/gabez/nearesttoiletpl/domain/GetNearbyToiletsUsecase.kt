package com.gabez.nearesttoiletpl.domain

import com.gabez.nearesttoiletpl.data.interfaces.AppRepository
import com.gabez.nearesttoiletpl.location.SearchBoundaries
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response
import javax.inject.Inject

class GetNearbyToiletsUsecase {

    @Inject
    lateinit var appRepository: AppRepository

    suspend fun invoke(bounds: SearchBoundaries): Response<String> =
        appRepository.getNearbyToilets(bounds)
}