package com.gabez.nearesttoiletpl.ui.fragments.select_language

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.gabez.nearesttoiletpl.R
import com.gabez.nearesttoiletpl.SharedPreferenceKeys
import com.google.android.material.button.MaterialButton


class SelectLanguageFragment : Fragment() {

    private var languageString: String = "ENG";
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
        radioGroup.setOnCheckedChangeListener { radioGroup, id ->
            when(id){
                R.id.language_ENG -> languageString = "ENG"
                R.id.language_PL -> languageString = "PL"
            }
        }
    }

    private fun initOkButton(view: View){
        val okButton: MaterialButton = view.findViewById(R.id.buttonOK)
        okButton.setOnClickListener {
            val prefs: SharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
            with (prefs.edit()) {
                putString(SharedPreferenceKeys.languageOption, languageString)
                apply()
            }

            navController.navigate(R.id.action_selectLanguageFragment_to_splashFragment);
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SelectLanguageFragment()
    }
}