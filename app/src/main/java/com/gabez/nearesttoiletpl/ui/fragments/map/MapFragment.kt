package com.gabez.nearesttoiletpl.ui.fragments.map

import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.gabez.nearesttoiletpl.R
import com.gabez.data_access.LocationUtils
import com.gabez.data_access.data.ApiResponseStatus
import com.gabez.nearesttoiletpl.domain.entity.Toilet
import com.gabez.nearesttoiletpl.ui.CurrentActivityUtil
import com.gabez.nearesttoiletpl.ui.fragments.rate_toilet.OpenRateToiletCallback
import com.gabez.nearesttoiletpl.ui.fragments.toilet_details.ToiletDetailsFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener, OpenRateToiletCallback {

    private lateinit var map: GoogleMap

    private val viewModel: MapViewModel  by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_map, container, false)

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        CurrentActivityUtil.currentActivityClassName = this.javaClass.name
        return view
    }

    companion object {
        fun newInstance() = MapFragment()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        val success = map.setMapStyle(
            MapStyleOptions.loadRawResourceStyle(
                requireContext(), R.raw.map_style
            )
        )

        if (!success) {
            Log.e("MAP_ERROR", "Style parsing failed.")
        }


        val location: Location? = LocationUtils.getUserLocation(requireContext())

        if (location != null) {
            moveMapToLocation(location)

            getNearbyToilets()
            map.setOnMarkerClickListener(this)

        } else Toast.makeText(requireContext(), "Location error!", Toast.LENGTH_LONG).show()
    }

    private fun getNearbyToilets(){
        viewModel.getNearbyToilets(requireContext(), 2000);
        viewModel.getNearbyToiletsLiveData().observe(this, Observer { response ->
            if (response != null) {
                when (response.status) {

                    ApiResponseStatus.OK -> {
                        Log.i("API_RES", response.data.toString())
                        setupPins(response.data as List<Toilet>)
                    }

                    ApiResponseStatus.NOT_OK -> {
                        Toast.makeText(
                            requireContext(),
                            "Api response is not ok! Contact developer for more information...",
                            Toast.LENGTH_LONG
                        )
                            .show()

                        Log.e("API_ERROR", response.optionalMessage + " <- error")
                    }

                    ApiResponseStatus.ERROR -> {
                        Toast.makeText(
                            requireContext(),
                            "An error occured! Contact developer for more information...",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                }
            } else Toast.makeText(requireContext(), "null response", Toast.LENGTH_LONG).show()
        })
    }

    fun setupPins(toilets: List<Toilet>){
        for(toilet in toilets){
            val position = LatLng(toilet.lat, toilet.lon)
            val markeroptions = MarkerOptions()
                .position(position)
                .title(toilet.displayName)
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

        val bottomFragment: ToiletDetailsFragment = ToiletDetailsFragment(currentToilet!!, requireActivity(), this)
        bottomFragment.showBottomSheetDialog();

        return true;
    }

    override fun openRateToiletFragment() {
        findNavController().navigate(R.id.action_mapFragment_to_rateToiletFragment)
    }

}