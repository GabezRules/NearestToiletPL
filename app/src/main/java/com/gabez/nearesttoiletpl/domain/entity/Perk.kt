package com.gabez.nearesttoiletpl.domain.entity

import android.graphics.drawable.Drawable
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Perk(
    var id: Int,
    var namePl: String,
    var nameEng: String,
    var icon: Drawable,
    var votes: Int = 0
)