package xyz.painapp.pocketdoc.fragments

import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import xyz.painapp.pocketdoc.R

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [LoadingFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [LoadingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoadingFragment : Fragment() {

    // TODO: Rename and change types of parameters


    private var mListener: OnFragmentInteractionListener? = null
    private var responseCode: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            responseCode = arguments.getString(ARG_RESPONSE_CODE)
        }

        if (responseCode != null && responseCode != "200") {
            val builder = AlertDialog.Builder(activity)

            builder.setMessage(R.string.error_server_message)
                    .setTitle(R.string.error_dialog_title)

            builder.setPositiveButton(R.string.ok) { _, _ ->
                activity.onBackPressed()
            }

            builder.create().show()
        }

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_loading, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
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
     //       throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
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
        private val ARG_RESPONSE_CODE = "reponseCode"

        fun newInstance(vararg responseCode: String): LoadingFragment {
            val fragment = LoadingFragment()

            if (responseCode.isNotEmpty()) {
                val args = Bundle()
                args.putString(ARG_RESPONSE_CODE, responseCode[0])
                fragment.arguments = args
            }

            return fragment
        }
    }
}// Required empty public constructor
