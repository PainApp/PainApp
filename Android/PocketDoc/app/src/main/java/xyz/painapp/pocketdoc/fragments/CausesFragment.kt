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

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [RegionFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [RegionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CausesFragment : Fragment() {

    private lateinit var causeRecyclerView: RecyclerView
    private lateinit var specificBodyRegion: SpecificBodyRegion
    private lateinit var causeTitleTextView: TextView
    private var mListener: OnFragmentInteractionListener? = null

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
        } else {
            Toast.makeText(activity, getString(R.string.no_data, specificBodyRegion.name), Toast.LENGTH_SHORT).show()
            activity.onBackPressed()
        }
    }

    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
//            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_SPECIFIC_REGION = "specific_region"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.

         * @return A new instance of fragment CausesFragment.
         */
        fun newInstance(specificBodyRegion: SpecificBodyRegion): CausesFragment {
            val fragment = CausesFragment()
            val args = Bundle()
            args.putParcelable(ARG_SPECIFIC_REGION, specificBodyRegion)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
