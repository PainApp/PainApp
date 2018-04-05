package xyz.painapp.pocketdoc.fragments

import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import xyz.painapp.pocketdoc.R
import xyz.painapp.pocketdoc.activities.BodyActivity
import xyz.painapp.pocketdoc.activities.DebugActivity

/**
 * Created by keyur on 2/5/18.
 * Package: xyz.painapp.pocketdoc.fragments as part of PocketDoc
 */
class MainFragment : Fragment(), AdapterView.OnItemClickListener {
    private lateinit var actionListView: ListView
    private lateinit var progressBar: ProgressBar
    lateinit var mCallback: OnMainActionSelectedListener

    interface OnMainActionSelectedListener {
        fun onMainActionSelected(action: String)
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

        actionListView.adapter = ArrayAdapter.createFromResource(activity, R.array.main_action_list, R.layout.main_action_list_item)
        actionListView.onItemClickListener = this
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        mCallback.onMainActionSelected(actionListView.getItemAtPosition(position) as String)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            mCallback = activity as OnMainActionSelectedListener
        } catch (e: ClassCastException) {
            throw ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    fun setProgressVisibility(visibility: Boolean) {
        if (!visibility) {
            actionListView.visibility = View.VISIBLE
            progressBar.visibility = View.INVISIBLE
        } else  {
            actionListView.visibility = View.INVISIBLE
            progressBar.visibility = View.VISIBLE
        }
    }


    companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }
}