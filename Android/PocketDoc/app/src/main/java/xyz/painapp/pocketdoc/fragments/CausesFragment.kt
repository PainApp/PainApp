package xyz.painapp.pocketdoc.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

import xyz.painapp.pocketdoc.R
import xyz.painapp.pocketdoc.adapters.CausesRecylerViewAdapter
import xyz.painapp.pocketdoc.entities.Cause
import xyz.painapp.pocketdoc.entities.SpecificBodyRegion


class CausesFragment : Fragment() {

    private lateinit var causeRecyclerView: RecyclerView
    private lateinit var specificBodyRegion: SpecificBodyRegion
    private lateinit var causeTitleTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            specificBodyRegion = arguments.getParcelable(ARG_SPECIFIC_REGION)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_causes, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (specificBodyRegion.causeList.size > 0) {
            causeRecyclerView = getView().findViewById(R.id.cause_recyclerView)
            causeTitleTextView = getView().findViewById(R.id.cause_title_textView)

            causeTitleTextView.text = getString(R.string.causes_title, specificBodyRegion.getFullName())

            causeRecyclerView.setHasFixedSize(true)
            causeRecyclerView.layoutManager = LinearLayoutManager(activity)

            causeRecyclerView.adapter = CausesRecylerViewAdapter(specificBodyRegion.causeList)
        }
    }


    companion object {
        private const val ARG_SPECIFIC_REGION = "specific_region"

        @JvmStatic
        fun newInstance(specificBodyRegion: SpecificBodyRegion) =
                CausesFragment().apply {
                    arguments = Bundle().apply { putParcelable(ARG_SPECIFIC_REGION, specificBodyRegion) }
                }
    }
}
