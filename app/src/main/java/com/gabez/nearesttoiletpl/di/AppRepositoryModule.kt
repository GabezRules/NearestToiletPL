package com.gabez.nearesttoiletpl.di

import com.gabez.nearesttoiletpl.data.AppRepositoryImpl
import com.gabez.nearesttoiletpl.data.interfaces.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class AppRepositoryModule {

    @Provides
    fun provideAppRepository(): AppRepository{
        return AppRepositoryImpl()
    }
}