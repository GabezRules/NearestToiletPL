package com.gabez.nearesttoiletpl.ui.fragments.select_language

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.gabez.nearesttoiletpl.R
import com.gabez.nearesttoiletpl.SharedPreferenceKeys
import com.gabez.nearesttoiletpl.language_options.LanguageOption
import com.gabez.nearesttoiletpl.language_options.LanguageOptionsHelper
import com.google.android.material.button.MaterialButton
import java.util.*


class SelectLanguageFragment : Fragment() {

    private var languageString: String = "en_US";
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_select_language, container, false)
        initSelect(view)
        initOkButton(view)
        return view
    }

    private fun initSelect(view: View){
        val radioGroup: RadioGroup = view.findViewById(R.id.language_select)
        radioGroup.setOnCheckedChangeListener { rGroup, id ->
            when(id){
                R.id.language_ENG -> languageString = LanguageOption.ENG.languageString
                R.id.language_PL -> languageString = LanguageOption.PL.languageString
            }
        }
    }

    private fun initOkButton(view: View){
        val okButton: MaterialButton = view.findViewById(R.id.buttonOK)
        okButton.setOnClickListener {
            val prefs: SharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)

            with(prefs.edit()) {
                putString(SharedPreferenceKeys.languageOption, languageString)
                apply()
            }

            LanguageOptionsHelper.setLanguage(languageString, requireContext())
            navController.navigate(R.id.action_selectLanguageFragment_to_splashFragment);
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SelectLanguageFragment()
    }
}