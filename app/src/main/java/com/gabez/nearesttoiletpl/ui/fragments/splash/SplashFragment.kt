package com.gabez.nearesttoiletpl.ui.fragments.splash

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gabez.nearesttoiletpl.R
import com.gabez.nearesttoiletpl.SharedPreferenceKeys
import com.gabez.nearesttoiletpl.language_options.LanguageOptionsHelper
import com.gabez.nearesttoiletpl.location.LocationUtils


class SplashFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isLanguageSelected = requireActivity().getPreferences(Context.MODE_PRIVATE)
            .getString(SharedPreferenceKeys.languageOption, "false")

        if (isLanguageSelected == "false") {
            findNavController().navigate(R.id.action_splashFragment_to_selectLanguageFragment)
        } else {
            LanguageOptionsHelper.setLanguage(isLanguageSelected!!, requireContext())
        }

        if (LocationUtils.getUserCountry(requireContext()) != LocationUtils.desiredLocation) {
            findNavController().navigate(R.id.action_splashFragment_to_wrongLocationFragment);
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_splash, container, false)

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = SplashFragment()
    }
}