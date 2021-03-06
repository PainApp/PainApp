package xyz.painapp.pocketdoc.fragments

import android.content.Context
import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView

import xyz.painapp.pocketdoc.R

class AboutFragment : Fragment(), AdapterView.OnItemClickListener {

    private var listener: OnAboutFragmentInteractionListener? = null
    private var aboutListView: ListView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        aboutListView = getView().findViewById(R.id.about_listView)

        aboutListView?.adapter = ArrayAdapter.createFromResource(activity, R.array.about_action_list, android.R.layout.simple_list_item_1)
        aboutListView?.onItemClickListener = this

    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        listener?.onListItemClicked(aboutListView!!.getItemAtPosition(position) as String)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnAboutFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnAboutFragmentInteractionListener {
        fun onListItemClicked(item: String)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                AboutFragment()
    }
}
