package com.gabez.nearesttoiletpl.di

import com.gabez.data_access.data.interfaces.AppRepository
import com.gabez.nearesttoiletpl.domain.GetNearbyToiletsUsecase
import com.gabez.nearesttoiletpl.domain.GetUserCountryUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class UsecasesModule {

    @Provides
    @ViewModelScoped
    fun provideGetNearbyToiletsUsecase(appRepo: AppRepository): GetNearbyToiletsUsecase{
        return GetNearbyToiletsUsecase(appRepo)
    }

    @Provides
    @ViewModelScoped
    fun provideGetUserCountry(appRepo: AppRepository): GetUserCountryUsecase{
        return GetUserCountryUsecase(appRepo)
    }
}