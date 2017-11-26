package xyz.painapp.pocketdoc.fragments

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

class SettingsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.settings_view, container, false)
    }
}