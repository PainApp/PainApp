package xyz.painapp.pocketdoc

/**
 * Created by keyur on 11/7/17.
 */

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by keyur on 11/7/17.
 */

class AboutFragment : Fragment() {
    companion object {
        val TITLE = "About"
    }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.about_view, container, false)
    }
}