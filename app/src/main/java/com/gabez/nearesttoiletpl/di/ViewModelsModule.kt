package com.gabez.nearesttoiletpl.di

import com.gabez.nearesttoiletpl.domain.GetNearbyToiletsUsecase
import com.gabez.nearesttoiletpl.domain.GetUserCountryUsecase
import com.gabez.nearesttoiletpl.ui.fragments.map.MapViewModel
import com.gabez.nearesttoiletpl.ui.fragments.splash.SplashViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelsModule {

    @Provides
    fun provideMapViewModel(getNearbyToiletsUsecase: GetNearbyToiletsUsecase): MapViewModel{
        return MapViewModel(getNearbyToiletsUsecase);
    }

    @Provides
    fun provideSplashViewModel(getUserCountryUsecase: GetUserCountryUsecase): SplashViewModel{
        return SplashViewModel(getUserCountryUsecase);
    }
}