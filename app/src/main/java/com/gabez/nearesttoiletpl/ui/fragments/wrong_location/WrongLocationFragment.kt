package com.gabez.nearesttoiletpl.ui.fragments.wrong_location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.recreate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gabez.nearesttoiletpl.R
import com.google.android.material.button.MaterialButton
import java.util.*
import kotlin.concurrent.schedule


class WrongLocationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_wrong_location, container, false)

        val buttonRetry: MaterialButton = view.findViewById(R.id.buttonRetry)
        buttonRetry.setOnClickListener {
            buttonRetry.visibility = View.INVISIBLE
            Timer("Reload", false).schedule(1000) {
                requireActivity().runOnUiThread(Runnable {  findNavController().navigate(R.id.action_wrongLocationFragment_to_splashFragment); })
            }
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = WrongLocationFragment()
    }
}