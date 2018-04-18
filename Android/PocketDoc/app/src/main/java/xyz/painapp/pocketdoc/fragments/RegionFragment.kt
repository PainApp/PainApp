package xyz.painapp.pocketdoc.fragments

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import xyz.painapp.pocketdoc.R
import xyz.painapp.pocketdoc.adapters.SpecificRegionRecyclerViewAdapter
import xyz.painapp.pocketdoc.entities.BodyRegion
import xyz.painapp.pocketdoc.entities.SpecificBodyRegion


class RegionFragment : Fragment() {

    private lateinit var bodyRegion: BodyRegion
    private lateinit var titleView: TextView
    private lateinit var regionListView: RecyclerView

    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            bodyRegion = arguments.getParcelable(ARG_BODY_REGION)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_region, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (bodyRegion.specificRegionList.size > 0) {
            titleView = getView().findViewById(R.id.specific_region_title)
            regionListView = getView().findViewById(R.id.sRegion_recyclerView)
            titleView.text = resources.getString(R.string.specific_region_title, bodyRegion.name)


            regionListView.setHasFixedSize(true)
            regionListView.layoutManager = LinearLayoutManager(activity)

            regionListView.adapter = SpecificRegionRecyclerViewAdapter(bodyRegion.specificRegionList)
       }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface OnFragmentInteractionListener {
        fun onRegionClicked(sRegion: SpecificBodyRegion)
    }

    companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_BODY_REGION = "body_region"

        @JvmStatic
        fun newInstance(region: BodyRegion) =
                RegionFragment().apply {
                    arguments = Bundle().apply { putParcelable(ARG_BODY_REGION, region) }
        }
    }
}
