package xyz.painapp.pocketdoc.fragments

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import xyz.painapp.pocketdoc.R


class LegalFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_legal, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                LegalFragment()
    }
}
