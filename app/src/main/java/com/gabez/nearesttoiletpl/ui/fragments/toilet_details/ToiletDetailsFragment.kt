package com.gabez.nearesttoiletpl.ui.fragments.toilet_details

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import com.gabez.nearesttoiletpl.domain.entity.Toilet
import com.gabez.nearesttoiletpl.R
import com.gabez.nearesttoiletpl.SharedPreferenceKeys
import com.gabez.nearesttoiletpl.language_options.LanguageOption
import com.gabez.nearesttoiletpl.ui.fragments.rate_toilet.OpenRateToiletCallback
import com.google.android.material.bottomsheet.BottomSheetDialog

class ToiletDetailsFragment(val toilet: Toilet, val context: Activity, val openRateToiletCallback: OpenRateToiletCallback) {
    private var bottomSheetDialog: BottomSheetDialog = BottomSheetDialog(context)

    fun showBottomSheetDialog() {
        bottomSheetDialog.setContentView(R.layout.fragment_toilet_details)

        val title = bottomSheetDialog.findViewById<TextView>(R.id.toiletName)!!
        val rateCount = bottomSheetDialog.findViewById<TextView>(R.id.rateCount)!!
        val ratingBar = bottomSheetDialog.findViewById<RatingBar>(R.id.ratingBar)!!
        val navigateBtn = bottomSheetDialog.findViewById<LinearLayout>(R.id.navigateBtn)!!
        val rateBtn = bottomSheetDialog.findViewById<LinearLayout>(R.id.rateBtn)!!

        bottomSheetDialog.show()

        navigateBtn.setOnClickListener { hideBottomSheetDialog() }

        rateCount.text = "(${toilet.rateCount})"
        ratingBar.rating = toilet.rate
        title.text = toilet.displayName

        rateBtn.setOnClickListener {
            hideBottomSheetDialog()
            openRateToiletCallback.openRateToiletFragment()
        }

        initPerks()
    }

    private fun initPerks() {
        val perksContainer = bottomSheetDialog.findViewById<LinearLayout>(R.id.perksContainer)!!
        val divider3 = bottomSheetDialog.findViewById<View>(R.id.divider3)!!

        val prefs: SharedPreferences = context.getPreferences(Context.MODE_PRIVATE)

        val languageString =
            prefs.getString(SharedPreferenceKeys.languageOption, LanguageOption.ENG.languageString)

        if (toilet.perkIds.size == 0) {
            perksContainer.visibility = View.GONE
            divider3.visibility = View.GONE
        } else {
            perksContainer.removeAllViews()

            val inflater: LayoutInflater = context.layoutInflater

            for (perk in toilet.perkIds) {
                val perkView: View = inflater.inflate(R.layout.view_perk, null)
                val icon = perkView.findViewById<ImageView>(R.id.perkIcon);
                val perkText = perkView.findViewById<TextView>(R.id.perkText);

                //TODO: Get perks from database
                /*
                if (languageString == LanguageOption.ENG.languageString) perkText.text =
                    "(${perk.votes})${perk.nameEng}"
                else perkText.text = "(${perk.votes})${perk.namePl}"

                icon.setImageDrawable(perk.icon)
                */

                perksContainer.addView(perkView)
            }
        }
    }

    fun hideBottomSheetDialog() {
        bottomSheetDialog.hide()
    }
}