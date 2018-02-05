package xyz.painapp.pocketdoc.fragments

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import xyz.painapp.pocketdoc.R
import xyz.painapp.pocketdoc.activities.BodyActivity
import xyz.painapp.pocketdoc.activities.DebugActivity

/**
 * Created by keyur on 2/5/18.
 * Package: xyz.painapp.pocketdoc.fragments as part of PocketDoc
 */
class MainFragment : Fragment(), AdapterView.OnItemClickListener {
    private lateinit var actionListView: ListView
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_main, container, false)
    }
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        actionListView = getView().findViewById(R.id.main_action_list)

        actionListView.adapter = ArrayAdapter.createFromResource(activity, R.array.main_action_list, R.layout.main_action_list_item)
        actionListView.onItemClickListener = this

    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val item = actionListView.getItemAtPosition(position) as String

        // TODO add in handlers for other activities
        val intent : Intent? =
                when (item) {
                    "start" -> Intent(activity, BodyActivity::class.java)
                    "debug" -> Intent(activity, DebugActivity::class.java)
                    else -> null
                }

        if (intent != null) {
            startActivity(intent)
        } else {
            Toast.makeText(activity, "That action isn't supported yet!", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }
}