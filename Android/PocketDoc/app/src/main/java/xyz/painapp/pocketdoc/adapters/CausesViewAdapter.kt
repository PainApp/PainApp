package xyz.painapp.pocketdoc.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import xyz.painapp.pocketdoc.entities.Cause
import android.view.LayoutInflater
import android.widget.TextView
import xyz.painapp.pocketdoc.R


/**
* Created by keyur on 11/25/17.
* Package: ${PACKAGE_NAME} as part of PocketDoc
*/

class CausesViewAdapter(private var context: Context, private var causes: ArrayList<Cause>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        var gridView: View

        if (convertView == null) {

            View(context)

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.grid_layout_item, null)

            // set value into textview
            val textView = gridView
                    .findViewById<TextView>(R.id.grid_item_label) as TextView
            textView.text = causes[position].cause
            textView.setBackgroundColor(causes[position].getBackgroundColor())
        } else {
            gridView = convertView
        }

        return gridView
    }

    override fun getItem(position: Int): Any {
        return causes[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return causes.size
    }

}