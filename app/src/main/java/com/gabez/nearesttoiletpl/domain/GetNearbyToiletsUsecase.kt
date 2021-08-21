package com.gabez.nearesttoiletpl.domain

import com.gabez.nearesttoiletpl.data.AppRepositoryImpl
import com.gabez.nearesttoiletpl.data.interfaces.AppRepository
import com.gabez.nearesttoiletpl.location.SearchBoundaries
import retrofit2.Response

class GetNearbyToiletsUsecase {
    private val appRepository: AppRepository = AppRepositoryImpl.instance()

    suspend fun invoke(bounds: SearchBoundaries): Response<String> = appRepository.getNearbyToilets(bounds)

    companion object{
        fun instance(): GetNearbyToiletsUsecase = GetNearbyToiletsUsecase()
    }
}