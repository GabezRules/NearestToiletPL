package com.gabez.nearesttoiletpl.ui.fragments.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.gabez.data_access.LocationUtils
import com.gabez.data_access.data.ApiResponseStatus
import com.gabez.nearesttoiletpl.R
import com.gabez.nearesttoiletpl.SharedPreferenceKeys
import com.gabez.nearesttoiletpl.language_options.LanguageOptionsHelper
import com.gabez.nearesttoiletpl.ui.fragments.MainActivity
import com.gabez.nearesttoiletpl.ui.fragments.StartActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private val viewModel: SplashViewModel by viewModels()

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
            viewModel.getUserLocationLiveData().observe(viewLifecycleOwner, Observer { response ->
                if (response != null) {
                    when (response.status) {
                        ApiResponseStatus.OK -> {
                            if (response.data!!.toString().equals("Poland")) {

                                val i: Intent = Intent(
                                    StartActivity.startActivityContext,
                                    MainActivity::class.java
                                )
                                startActivity(i)
                                StartActivity.startActivityContext.finish()

                            } else {
                                findNavController().navigate(R.id.action_splashFragment_to_wrongLocationFragment)
                            }
                        }

                        ApiResponseStatus.NOT_OK -> {
                            Toast.makeText(
                                context,
                                "Api response is not ok! Contact developer for more information...",
                                Toast.LENGTH_LONG
                            )
                                .show()

                            findNavController().navigate(R.id.action_splashFragment_to_wrongLocationFragment)
                        }

                        ApiResponseStatus.ERROR -> {
                            Toast.makeText(
                                context,
                                "An error occured! Contact developer for more information...",
                                Toast.LENGTH_LONG
                            )
                                .show()

                            findNavController().navigate(R.id.action_splashFragment_to_wrongLocationFragment)
                        }
                    }
                } else Toast.makeText(context, "null response", Toast.LENGTH_LONG).show()
            })
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = SplashFragment()
    }
}