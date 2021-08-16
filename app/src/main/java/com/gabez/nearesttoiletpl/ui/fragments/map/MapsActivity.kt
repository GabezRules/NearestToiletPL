package com.gabez.nearesttoiletpl.ui.fragments.map

import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.gabez.nearesttoiletpl.R
import com.gabez.nearesttoiletpl.api.ApiResponseStatus
import com.gabez.nearesttoiletpl.domain.entity.Toilet
import com.gabez.nearesttoiletpl.location.LocationUtils
import com.gabez.nearesttoiletpl.ui.CurrentActivityUtil
import com.gabez.nearesttoiletpl.ui.fragments.toilet_details.ToiletDetailsFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var map: GoogleMap
    private lateinit var viewModel: MapViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        CurrentActivityUtil.currentActivityClassName = this.javaClass.name
        viewModel = MapViewModel.instance();
    }
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        val success = map.setMapStyle(
            MapStyleOptions.loadRawResourceStyle(
                this, R.raw.map_style
            )
        )

        if (!success) {
            Log.e("MAP_ERROR", "Style parsing failed.")
        }


        val location: Location? = LocationUtils.getUserLocation(this)

        if (location != null) {
            moveMapToLocation(location)

            getNearbyToilets()
            map.setOnMarkerClickListener(this)

        } else Toast.makeText(this, "Location error!", Toast.LENGTH_LONG).show()
    }

    fun getNearbyToilets(){
        viewModel.getNearbyToilets(this, 2000);
        viewModel.getNearbyToiletsLiveData().observe(this, Observer { response ->
            if (response != null) {
                when (response.status) {

                    ApiResponseStatus.OK -> {
                        Log.i("API_RES", response.data.toString())
                        Toast.makeText(this, "Pobieranie udane!", Toast.LENGTH_LONG).show()
                        setupPins(response.data as List<Toilet>)
                    }

                    ApiResponseStatus.NOT_OK -> {
                        Toast.makeText(
                            this,
                            "Api response is not ok! Contact developer for more information...",
                            Toast.LENGTH_LONG
                        )
                            .show()

                        Log.e("API_ERROR", response.optionalMessage + " <- error")
                    }

                    ApiResponseStatus.ERROR -> {
                        Toast.makeText(
                            this,
                            "An error occured! Contact developer for more information...",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                }
            } else Toast.makeText(this, "null response", Toast.LENGTH_LONG).show()
        })
    }

    fun setupPins(toilets: List<Toilet>){
        for(toilet in toilets){
            val position = LatLng(toilet.lat, toilet.lon)
            val markeroptions = MarkerOptions()
                .position(position)
                .title(toilet.neighbourhood + ", " + toilet.road)
                .snippet(toilet.displayName)

            map.addMarker(
                markeroptions
            )
        }

        val mapLocation = Location(toilets[0].displayName)
        mapLocation.latitude = toilets[0].lat;
        mapLocation.longitude = toilets[0].lon;

        moveMapToLocation(mapLocation)
    }

    fun moveMapToLocation(location: Location){
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
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        var currentToilet: Toilet? = null
        viewModel.currentToiletList.map { toilet ->  if(toilet.displayName == marker.snippet) currentToilet = toilet}

        val bottomFragment: ToiletDetailsFragment = ToiletDetailsFragment(currentToilet!!, this)
        bottomFragment.showBottomSheetDialog();

        return true;
    }
}