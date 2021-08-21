package com.gabez.nearesttoiletpl.di

import com.gabez.nearesttoiletpl.data.AppRepositoryImpl
import com.gabez.nearesttoiletpl.data.interfaces.AppRepository
import com.gabez.nearesttoiletpl.data.interfaces.UserCountryDatasource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppRepositoryModule {

    @Provides
    fun provideAppRepository(userCountryDatasource: UserCountryDatasource): AppRepository{
        return AppRepositoryImpl(userCountryDatasource)
    }
}