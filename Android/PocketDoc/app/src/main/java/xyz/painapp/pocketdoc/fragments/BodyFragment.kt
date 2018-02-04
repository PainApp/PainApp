package xyz.painapp.pocketdoc.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.app.Fragment
import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView

import xyz.painapp.pocketdoc.R
import xyz.painapp.pocketdoc.activities.BodyActivity
import xyz.painapp.pocketdoc.activities.RegionActivity
import xyz.painapp.pocketdoc.entities.BodyRegion
import xyz.painapp.pocketdoc.entities.HTTPUrlMethod

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [RegionFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [RegionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BodyFragment : Fragment(), View.OnClickListener {

    private lateinit var flipButton: Button
    private lateinit var bodyConstraintLayout: ConstraintLayout
    private lateinit var bodyRegionList: ArrayList<BodyRegion>
    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            bodyRegionList = arguments.getParcelableArrayList(ARG_BODY_LIST)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_body, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bodyConstraintLayout = getView().findViewById(R.id.body_constraintLayout)
        flipButton = getView().findViewById(R.id.flip_body_button)
        (0..bodyConstraintLayout.childCount)
                .map { bodyConstraintLayout.getChildAt(it) }
                .forEach { (it as? ImageView)?.setOnClickListener(this) }

        //TODO implement flip button with new picture
        flipButton!!.setOnClickListener(this)
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


    override fun onClick(v: View?) {

        when (v!!.id) {
            R.id.flip_body_button -> return
            else -> {
                val found: BodyRegion? = (0 until bodyRegionList.size)
                        .firstOrNull { bodyRegionList[it].viewId == v.id }
                        ?.let { bodyRegionList[it] }
                if (found != null) {

                    (activity as BodyActivity).DownloadRegionInfoTask().execute(HTTPUrlMethod(
                            HTTPUrlMethod.BODY_REGION_URL,
                            HTTPUrlMethod.POST,
                            found.toJSONObject()
                    )
                    )
                }
            }
        }
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
        private val ARG_BODY_LIST = "body_list"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.

         * @return A new instance of fragment RegionFragment.
         */
        fun newInstance(bodyRegionList: ArrayList<BodyRegion>): BodyFragment {
            val fragment = BodyFragment()
            val args = Bundle()
            args.putParcelableArrayList(ARG_BODY_LIST, bodyRegionList)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
