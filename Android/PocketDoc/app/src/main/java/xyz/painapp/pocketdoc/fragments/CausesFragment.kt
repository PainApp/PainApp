package xyz.painapp.pocketdoc.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridView
import xyz.painapp.pocketdoc.activities.BodyActivity
import xyz.painapp.pocketdoc.adapters.CausesViewAdapter
import xyz.painapp.pocketdoc.entities.Cause
import xyz.painapp.pocketdoc.R

/**
* Created by keyur on 11/25/17.
* Package: ${PACKAGE_NAME} as part of PocketDoc
*/
class CausesFragment : Fragment(), AdapterView.OnItemClickListener {
    private var gridView: GridView? = null
    private var causes: ArrayList<Cause> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //TODO get bundle
        causes = ArrayList()
        causes.add(Cause("Cause 1", 3))
        causes.add(Cause("Cause 2", 2))
        causes.add(Cause("Cause 3", 1))
        causes.add(Cause("Cause 4", 0))

        return inflater!!.inflate(R.layout.causes_view, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        gridView = getView()!!.findViewById(R.id.cause_grid_view)

        gridView!!.adapter = CausesViewAdapter(context, causes)

        gridView!!.onItemClickListener = this

    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val causeStr = causes.get(position).cause
        val data = Bundle()

        data.putString("Cause", causeStr)

        (activity as BodyActivity).switchFragments(DetailFragment(), causeStr, data)
    }
}