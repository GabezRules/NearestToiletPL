package com.gabez.nearesttoiletpl.ui.fragments.request_location

import android.Manifest
import android.Manifest.permission.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gabez.nearesttoiletpl.R
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RequestLocationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_request_location, container, false)

        val grantPermissionButton: MaterialButton = view.findViewById(R.id.buttonGrantPermission)
        grantPermissionButton.setOnClickListener {
            requestPermissionLauncher.launch(arrayOf(ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION, ACCESS_NETWORK_STATE))
        }

        return view
    }

    val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            permissions ->

            if (permissions[ACCESS_COARSE_LOCATION] == true && permissions[ACCESS_FINE_LOCATION] == true) {
                findNavController().navigate(R.id.action_requestLocationFragment_to_splashFragment)
            }
        }


    companion object {
        @JvmStatic
        fun newInstance() = RequestLocationFragment()
    }
}