package com.gabez.data_access

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.app.ActivityCompat


object LocationUtils {
    @SuppressLint("MissingPermission")
    fun getUserLocation(context: Context): Location? {
        if (hasLocationPermissions(context)) {
            val mLocationManager =
                context.getSystemService(
                    LOCATION_SERVICE
                ) as LocationManager
            val providers: List<String> = mLocationManager.getProviders(true)
            var bestLocation: Location? = null
            for (provider in providers) {
                val l: Location = mLocationManager.getLastKnownLocation(provider) ?: continue
                if (bestLocation == null || l.accuracy < bestLocation.accuracy) {
                    bestLocation = l
                }
            }
            return bestLocation
        } else return null
    }

    fun hasLocationPermissions(context: Context): Boolean {
        return (!(
                ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
                        ||
                        ActivityCompat.checkSelfPermission(
                            context,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED
                ))
    }

    fun getBoundariesForSearch(context: Context, distanceInMeters: Int): SearchBoundaries {
        //TODO: Check if is proper
        val userLocation: Location = getUserLocation(context)!!

        val oneLongitudeDegreeInKilometers = userLocation.latitude * 69.172f * 1.609344f
        val lonDistance = Math.sqrt(oneLongitudeDegreeInKilometers);

        val latDistance: Float = (distanceInMeters.toFloat())/1000/111

        val minLat: Double = userLocation.latitude - latDistance
        val maxLat: Double = userLocation.latitude + latDistance

        val minLon: Double = userLocation.longitude - lonDistance
        val maxLon: Double = userLocation.longitude + lonDistance

        return SearchBoundaries(minLon, minLat, maxLon, maxLat)
    }
}