package xyz.painapp.pocketdoc.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import xyz.painapp.pocketdoc.R
import xyz.painapp.pocketdoc.entities.BodyRegion

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [RegionFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [RegionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegionFragment : Fragment() {

    private lateinit var bodyRegion: BodyRegion

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
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
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
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_BODY_REGION = "Body Region"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.

         * @return A new instance of fragment RegionFragment.
         */
        fun newInstance(region: BodyRegion): RegionFragment {
            val fragment = RegionFragment()
            val args = Bundle()
            args.putParcelable("Region", region)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
