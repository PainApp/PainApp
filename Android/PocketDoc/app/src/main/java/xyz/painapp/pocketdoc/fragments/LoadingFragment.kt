package xyz.painapp.pocketdoc.fragments

import android.app.AlertDialog
import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import xyz.painapp.pocketdoc.R

class LoadingFragment : Fragment() {

    private var errorMessage: String? = null
    private var internetError: Boolean = false
    private var progressBar: ProgressBar? = null
    private var loadingTextView: TextView? = null

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
        loadingTextView = getView().findViewById(R.id.loading_textView)
        progressBar = getView().findViewById(R.id.progressBar)

        if (internetError) {
            loadingConstraintView.setBackgroundColor(ContextCompat.getColor(activity, R.color.colorPrimary))
            loadingTextView!!.setTextColor(ContextCompat.getColor(activity, R.color.white))
        }
    }

    fun setProgressVisible(visible: Boolean) {
        if (!visible) {
            progressBar?.visibility = View.INVISIBLE
            loadingTextView?.visibility = View.INVISIBLE
        } else {
            progressBar?.visibility = View.VISIBLE
            loadingTextView?.visibility = View.VISIBLE
        }
    }

    companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_ERROR_MESSAGE = "errorMessage"
        private const val ARG_INTERNET_ERROR = "internetError"

        @JvmStatic
        fun newInstance(errorMessage: String = "", internetError: Boolean = false) =
                LoadingFragment().apply {
                    if (errorMessage.isNotEmpty()) {
                        arguments = Bundle().apply {
                            putString(ARG_ERROR_MESSAGE, errorMessage)
                            putBoolean(ARG_INTERNET_ERROR, internetError)}
                    }
        }
    }
}// Required empty public constructor
