package br.com.testemeradolivre.util

import android.content.Context
import android.os.Build

import java.util.Locale

object Utils {

    fun getCurrentLocale(c: Context): Locale {

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            c.resources.configuration.locales.get(0)
        } else {
            c.resources.configuration.locale
        }
    }
}
