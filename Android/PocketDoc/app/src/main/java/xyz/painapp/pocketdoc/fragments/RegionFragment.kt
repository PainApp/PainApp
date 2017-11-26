package xyz.painapp.pocketdoc.fragments

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import xyz.painapp.pocketdoc.activities.BodyActivity
import xyz.painapp.pocketdoc.R

/**
* Created by keyur on 11/25/17.
* Package: ${PACKAGE_NAME} as part of PocketDoc
*/
class RegionFragment: Fragment(), AdapterView.OnItemClickListener, View.OnTouchListener {


    private var region = ""
    private var symptomsListView: ListView? = null
    private var regionImageView: ImageView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val args : Bundle?  = arguments
        region = args!!.getString("Region")

        return inflater!!.inflate(R.layout.region_view, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        symptomsListView = getView()!!.findViewById<ListView>(R.id.region_symptoms_list_view) as ListView
        regionImageView = getView()!!.findViewById<ImageView>(R.id.region_image_view) as ImageView

        // TODO implement dynamic data based on region argument
        symptomsListView!!.adapter = ArrayAdapter.createFromResource(context, R.array.sample_symptoms_list, R.layout.symptom_list_item)
        regionImageView!!.setImageResource(R.drawable.calf_region)

        symptomsListView!!.onItemClickListener = this
        regionImageView!!.setOnTouchListener(this)

    }


    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when (v) {
            regionImageView -> return imgButtonTouch(v, event)
            else -> v!!.performClick()
        }
        return true
    }

    //TODO get actual data passed around
    private fun imgButtonTouch(v: View?, event: MotionEvent?): Boolean {
        val x : Int = event!!.x.toInt()
        val y : Int = event.y.toInt()
        var data = Bundle()

        var symptom = getSymptom(x, y)


        data.putString("Symptom", symptom)

        (activity as BodyActivity).switchFragments(CausesFragment(), region, data)

        v!!.performClick()

        return true
    }


    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val symptom = symptomsListView!!.getItemAtPosition(position) as String
        val data = Bundle()

        data.putString("Symptom", symptom)

        (activity as BodyActivity).switchFragments(CausesFragment(), symptom, data)
    }

    private fun getSymptom(x: Int, y: Int): String {
        return "test"
    }
}