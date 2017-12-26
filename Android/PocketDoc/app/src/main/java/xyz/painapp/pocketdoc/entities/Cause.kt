package xyz.painapp.pocketdoc.entities

import android.graphics.Color


/**
* Created by keyur on 11/25/17.
* Package: ${PACKAGE_NAME} as part of PocketDoc
*/

class Cause(val cause: String, val likelihood: Int) {
    fun getBackgroundColor() : Int {
        val colorStr =
        when (likelihood) {
            3 -> "#ff6666"
            2 -> "#80ff80"
            1 -> "#ff99ff"
            else -> "#ffff1a"
        }

        return Color.parseColor(colorStr)
    }
}