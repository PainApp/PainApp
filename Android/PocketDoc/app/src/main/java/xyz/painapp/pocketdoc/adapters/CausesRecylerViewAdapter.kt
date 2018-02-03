package xyz.painapp.pocketdoc.adapters

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import xyz.painapp.pocketdoc.entities.Cause
import android.view.LayoutInflater
import android.widget.TextView
import xyz.painapp.pocketdoc.R


/**
* Created by keyur on 11/25/17.
* Package: ${PACKAGE_NAME} as part of PocketDoc
*/

class CausesRecylerViewAdapter : RecyclerView.Adapter<CausesRecylerViewAdapter.CausesViewHolder> {
    private var causes: ArrayList<Cause>

    constructor() {
        this.causes = ArrayList(0)
    }

    constructor(causes: ArrayList<Cause>): this() {
        this.causes = causes
    }



    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CausesRecylerViewAdapter.CausesViewHolder {
        val itemView: View = LayoutInflater.from(parent!!.context).inflate(R.layout.card_item_view, parent, false)
        return CausesViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return causes.size
    }

    override fun onBindViewHolder(holder: CausesViewHolder?, position: Int) {
        holder!!.setItem(causes[position])
    }

    class CausesViewHolder(parent: View) : RecyclerView.ViewHolder(parent) {
        var causeTextView: TextView = parent.findViewById(R.id.card_item_textView)

        fun setItem(item: Cause) {
            causeTextView.text = item.name
            causeTextView.setBackgroundColor(ContextCompat.getColor(causeTextView.context, item.getColor()))
        }
    }
}