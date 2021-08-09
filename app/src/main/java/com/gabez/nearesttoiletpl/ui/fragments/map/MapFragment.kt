package com.gabez.nearesttoiletpl.ui.fragments.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gabez.nearesttoiletpl.R

class MapFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_map, container, false)
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MapFragment()
    }
}