package com.gabez.nearesttoiletpl.location

import android.content.Context
import android.os.Build
import java.util.*


object LocationUtils {
    val desiredLocation: String = "PL"

    //TODO: Get phone location by GPS
    fun getUserCountry(context: Context): String{
        /*
        val locale: Locale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.resources.configuration.locales[0]
        } else {
            context.resources.configuration.locale
        }

        return locale.country;
        */
        return "PL"
    }
}