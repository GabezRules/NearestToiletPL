package com.gabez.nearesttoiletpl.ui.fragments.toilet_details

import android.content.Context
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import com.gabez.nearesttoiletpl.R
import com.gabez.nearesttoiletpl.domain.entity.Toilet
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ToiletDetailsFragment(val toilet: Toilet, val context: Context) {
    private var bottomSheetDialog: BottomSheetDialog

    init {
        bottomSheetDialog = BottomSheetDialog(context)
    }

    fun showBottomSheetDialog(){
        bottomSheetDialog.setContentView(R.layout.fragment_toilet_details)

        val title = bottomSheetDialog.findViewById<TextView>(R.id.toiletName)!!
        val rateCount = bottomSheetDialog.findViewById<TextView>(R.id.rateCount)!!
        val ratingBar = bottomSheetDialog.findViewById<RatingBar>(R.id.ratingBar)!!
        val navigateBtn = bottomSheetDialog.findViewById<LinearLayout>(R.id.navigateBtn)!!

        bottomSheetDialog.show()

        navigateBtn.setOnClickListener { hideBottomSheetDialog() }

        val titleString: String = toilet.displayName.split(", ").subList(0, 3).joinToString(", ")

        rateCount.text = "(${toilet.rateCount})"
        ratingBar.rating = toilet.rate
        title.text = titleString
    }

    fun hideBottomSheetDialog(){
        bottomSheetDialog.hide()
    }
}