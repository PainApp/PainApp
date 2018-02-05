package xyz.painapp.pocketdoc.fragments

import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.app.Fragment
import android.content.Intent
import android.provider.Settings
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

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

    private var mListener: OnFragmentInteractionListener? = null
    private var errorMessage: String? = null
    private var internetError: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            errorMessage = arguments.getString(ARG_ERROR_MESSAGE)
            internetError = arguments.getBoolean(ARG_INTERNET_ERROR)
        }

        if (errorMessage != null) {
            val builder = AlertDialog.Builder(activity)

            builder.setMessage(errorMessage)
                    .setTitle(R.string.error_dialog_title)

            if (!internetError) {
                builder.setPositiveButton(R.string.ok) { _, _ ->
                    activity.onBackPressed()
                }
            } else {
                builder.setPositiveButton(R.string.open_network_settings) { _, _ ->
                    startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
                }
            }

            builder.create().show()
        }


    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_loading, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loadingConstraintView: ConstraintLayout = getView().findViewById(R.id.loading_constraint_layout)
        val loadingTextView: TextView = getView().findViewById(R.id.loading_textView)

        if (internetError) {
            loadingConstraintView.setBackgroundColor(ContextCompat.getColor(activity, R.color.colorPrimary))
            loadingTextView.setTextColor(ContextCompat.getColor(activity, R.color.white))
        }
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
        private val ARG_ERROR_MESSAGE = "errorMessage"
        private val ARG_INTERNET_ERROR = "internetError"

        fun newInstance(errorMessage: String = "", internetError: Boolean = false): LoadingFragment {
            val fragment = LoadingFragment()

            if (errorMessage.isNotEmpty()) {
                val args = Bundle()
                args.putString(ARG_ERROR_MESSAGE, errorMessage)
                args.putBoolean(ARG_INTERNET_ERROR, internetError)
                fragment.arguments = args
            }

            return fragment
        }
    }
}// Required empty public constructor
