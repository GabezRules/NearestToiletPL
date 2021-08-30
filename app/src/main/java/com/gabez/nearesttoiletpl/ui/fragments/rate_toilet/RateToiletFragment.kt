package com.gabez.nearesttoiletpl.ui.fragments.rate_toilet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.gabez.nearesttoiletpl.R

class RateToiletFragment : Fragment() {

    private lateinit var btnAccept: Button
    private lateinit var btnCancel: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_rate_toilet, container, false)

        btnAccept = view.findViewById(R.id.btnAccept)
        btnCancel = view.findViewById(R.id.btnCancel)

        btnAccept.setOnClickListener {
            goBack()
        }

        btnCancel.setOnClickListener {
            goBack()
        }

        return view
    }

    private fun goBack(){
        findNavController().popBackStack()
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            RateToiletFragment()
    }
}