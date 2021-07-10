package com.gabez.nearesttoiletpl.ui.fragments.splash

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.gabez.nearesttoiletpl.R
import com.gabez.nearesttoiletpl.SharedPreferenceKeys
import com.gabez.nearesttoiletpl.api.ApiResponseStatus
import com.gabez.nearesttoiletpl.language_options.LanguageOptionsHelper
import com.gabez.nearesttoiletpl.location.LocationUtils

class SplashFragment : Fragment() {

    var viewModel: SplashViewModel = SplashViewModel.instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_splash, container, false)

        val isLanguageSelected = requireActivity().getPreferences(Context.MODE_PRIVATE)
            .getString(SharedPreferenceKeys.languageOption, "false")

        if (isLanguageSelected == "false") {
            findNavController().navigate(R.id.action_splashFragment_to_selectLanguageFragment)
        } else {
            LanguageOptionsHelper.setLanguage(isLanguageSelected!!, requireContext())
        }

        if (!LocationUtils.hasLocationPermissions(requireContext())) {
            findNavController().navigate(R.id.action_splashFragment_to_requestLocationFragment)
        } else {
            viewModel.isUserInDesiredLocation(requireContext())
                .observe(viewLifecycleOwner, Observer { response ->
                    if(response!=null){
                        when (response.status) {
                            ApiResponseStatus.OK -> {
                                if (response.data!!.toString().equals("Poland")) {
                                    Toast.makeText(requireContext(), "Alles klar!", Toast.LENGTH_LONG)
                                        .show()
                                } else {
                                    findNavController().navigate(R.id.action_splashFragment_to_wrongLocationFragment)
                                }
                            }

                            ApiResponseStatus.NOT_OK -> {
                                Toast.makeText(requireContext(), "Api response is not ok! Contact developer for more information...", Toast.LENGTH_LONG)
                                    .show()

                                Log.e("API_ERROR", response.optionalMessage+"")
                            }

                            ApiResponseStatus.ERROR -> {
                                Toast.makeText(requireContext(), "An error occured! Contact developer for more information...", Toast.LENGTH_LONG)
                                    .show()

                                Log.e("API_ERROR", response.optionalMessage+"")
                            }
                        }
                    }
                })
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = SplashFragment()
    }
}