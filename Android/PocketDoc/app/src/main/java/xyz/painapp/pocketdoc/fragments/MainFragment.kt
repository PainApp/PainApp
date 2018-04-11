package xyz.painapp.pocketdoc.fragments

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import xyz.painapp.pocketdoc.R

/**
 * Created by keyur on 2/5/18.
 * Package: xyz.painapp.pocketdoc.fragments as part of PocketDoc
 */
class MainFragment : Fragment(), AdapterView.OnItemClickListener {
    private var actionListView: ListView? = null
    private var progressBar: ProgressBar? = null
    private var progressBarVisible: Boolean = false
    private var mListener: OnMainActionSelectedListener? = null

    interface OnMainActionSelectedListener {
        fun onMainActionSelected(action: String)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            progressBarVisible = arguments.getBoolean(ARG_PROGRESS_VISIBLE)
        }
    }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_main, container, false)
    }
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        actionListView = getView().findViewById(R.id.main_action_list)
        progressBar = getView().findViewById(R.id.loading_bar)

        actionListView!!.adapter = ArrayAdapter.createFromResource(activity, R.array.main_action_list, R.layout.main_action_list_item)
        actionListView!!.onItemClickListener = this
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        mListener!!.onMainActionSelected(actionListView!!.getItemAtPosition(position) as String)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            mListener = activity as OnMainActionSelectedListener
        } catch (e: ClassCastException) {
            throw ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener")
        }
    }

    fun setProgressVisibility(visible: Boolean) {
        if (!visible) {
            actionListView?.visibility = View.VISIBLE
            progressBar?.visibility = View.INVISIBLE
        } else  {
            actionListView?.visibility = View.INVISIBLE
            progressBar?.visibility = View.VISIBLE
        }
    }

    fun setActionListVisibility(visible: Boolean) {
        if (!visible) {
            actionListView?.visibility = View.INVISIBLE
        } else {
            actionListView?.visibility = View.INVISIBLE
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }


    companion object {
        const val ARG_PROGRESS_VISIBLE = "progress_visible"
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        fun newInstance(progressBarVisible: Boolean): MainFragment {
            val fragment = MainFragment()
            val args = Bundle()
            args.putBoolean(ARG_PROGRESS_VISIBLE, progressBarVisible)
            fragment.arguments = args

            return fragment
        }
    }
}