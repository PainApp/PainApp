package xyz.painapp.pocketdoc.adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.TextView
import xyz.painapp.pocketdoc.R
import xyz.painapp.pocketdoc.activities.CausesActivity
import xyz.painapp.pocketdoc.activities.RegionActivity
import xyz.painapp.pocketdoc.entities.HTTPUrlMethod
import xyz.painapp.pocketdoc.entities.SpecificBodyRegion


/**
 * Created by keyur on 11/25/17.
 * Package: ${PACKAGE_NAME} as part of PocketDoc
 */

class SpecificRegionRecyclerViewAdapter : RecyclerView.Adapter<SpecificRegionRecyclerViewAdapter.RegionViewHolder> {
    private var sRegions: ArrayList<SpecificBodyRegion>

    constructor() {
        this.sRegions = ArrayList(0)
    }

    constructor(sRegions: ArrayList<SpecificBodyRegion>): this() {
        this.sRegions = sRegions
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecificRegionRecyclerViewAdapter.RegionViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.card_item_view, parent, false)
        return RegionViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return sRegions.size
    }

    override fun onBindViewHolder(holder: RegionViewHolder, position: Int) {
        holder.setItem(sRegions[position])
    }

    class RegionViewHolder(parent: View) : RecyclerView.ViewHolder(parent), View.OnClickListener {
        private var sRegion: SpecificBodyRegion? = null
        private var regionTextView: TextView = parent.findViewById(R.id.card_item_textView)

        override fun onClick(v: View?) {
            (v?.context as RegionActivity).DownloadCauseInfo().execute(HTTPUrlMethod(
                    HTTPUrlMethod.SPECIFIC_REGION_URL,
                    HTTPUrlMethod.POST,
                    sRegion?.toJSONObject()
            ))
            /*val intent = Intent(v!!.context, CausesActivity::class.java)
            intent.putExtra(SpecificBodyRegion.S_REGION_STR, sRegion)
            v.context.startActivity(intent)*/
        }

        fun setItem(item: SpecificBodyRegion) {
            regionTextView.text = item.name
            sRegion = item
        }

        init {
            parent.setOnClickListener(this)
        }


    }
}