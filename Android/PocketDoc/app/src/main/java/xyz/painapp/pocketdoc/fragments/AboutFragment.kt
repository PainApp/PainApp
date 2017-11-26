package xyz.painapp.pocketdoc.fragments

/**
* Created by keyur on 11/7/17.
* Package: ${PACKAGE_NAME} as part of PocketDoc
*/

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import xyz.painapp.pocketdoc.R

/**
* Created by keyur on 11/7/17.
* Package: ${PACKAGE_NAME} as part of PocketDoc
*/

class AboutFragment : Fragment() {
    companion object {
        val TITLE = "About"
    }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.about_view, container, false)
    }
}