package com.gabez.nearesttoiletpl.language_options

import android.content.Context
import android.content.res.Configuration
import java.util.*

object LanguageOptionsHelper {
    fun setLanguage(languageString: String, context: Context){
        val locale = Locale(languageString)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale

        context.resources.updateConfiguration(
            config,
            context.resources.displayMetrics
        )
    }
}