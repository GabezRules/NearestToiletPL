package com.gabez.nearesttoiletpl.ui.fragments

import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.gabez.nearesttoiletpl.R
import com.gabez.nearesttoiletpl.location.LocationUtils
import com.gabez.nearesttoiletpl.ui.CurrentActivityUtil

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        CurrentActivityUtil.currentActivityClassName = this.javaClass.name
    }
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        val location: Location? = LocationUtils.getUserLocation(this)

        if (location != null) {
            map.moveCamera(
                CameraUpdateFactory.newLatLng(
                    LatLng(
                        location.latitude,
                        location.longitude
                    )
                )
            )

            map.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(
                        location.latitude,
                        location.longitude
                    ), 20.0f
                )
            )

        } else Toast.makeText(this, "Location error!", Toast.LENGTH_LONG).show()
    }
}