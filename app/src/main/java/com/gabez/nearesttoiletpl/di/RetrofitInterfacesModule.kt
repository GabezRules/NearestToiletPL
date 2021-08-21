package com.gabez.nearesttoiletpl.di

import com.gabez.nearesttoiletpl.api.location_iq.LocationIQAutocompleteInterface
import com.gabez.nearesttoiletpl.api.location_iq.LocationIQReverseGeocodingInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class RetrofitInterfacesModule {

    @Provides
    fun provideAutocompleteinterface(): LocationIQAutocompleteInterface = LocationIQAutocompleteInterface.create()

    @Provides
    fun provideReverseGeocodingInterface(): LocationIQReverseGeocodingInterface = LocationIQReverseGeocodingInterface.create()
}