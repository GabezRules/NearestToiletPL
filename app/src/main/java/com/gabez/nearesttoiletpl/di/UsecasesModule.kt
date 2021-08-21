package com.gabez.nearesttoiletpl.di

import com.gabez.nearesttoiletpl.domain.GetNearbyToiletsUsecase
import com.gabez.nearesttoiletpl.domain.GetUserCountryUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class UsecasesModule {

    @Provides
    fun provideGetNearbyToiletsUsecase(): GetNearbyToiletsUsecase{
        return GetNearbyToiletsUsecase()
    }

    @Provides
    fun provideGetUserCountry(): GetUserCountryUsecase{
        return GetUserCountryUsecase()
    }
}