package com.gabez.nearesttoiletpl.ui.fragments.splash

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.gabez.nearesttoiletpl.location.LocationUtils

class SplashViewModel : ViewModel() {
    protected fun isUserInDesiredLocation(context: Context): Boolean {
        Log.e("LOCATION", LocationUtils.getUserCountry(context))
        return false;
    }
}