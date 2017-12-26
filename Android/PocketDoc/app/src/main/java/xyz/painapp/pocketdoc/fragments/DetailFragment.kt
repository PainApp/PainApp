package xyz.painapp.pocketdoc.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import xyz.painapp.pocketdoc.R

/**
* Created by keyur on 11/25/17.
* Package: ${PACKAGE_NAME} as part of PocketDoc
*/
class DetailFragment: Fragment() {
    private var primarySymptoms: ArrayList<String> = ArrayList()
    private var secondarySymptoms: ArrayList<String> = ArrayList()
    private lateinit var detailImageView: ImageView
    private lateinit var primarySymptomListView: ListView
    private lateinit var secondarySymptomListView: ListView
    private lateinit var detailTitleView: TextView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // TODO set title, images and symptoms based on actual data from bundle arg
        primarySymptoms = ArrayList()
        secondarySymptoms = ArrayList()
        primarySymptoms.add("Primary Symptom 1")
        primarySymptoms.add("Primary Symptom 2")
        primarySymptoms.add("Primary Symptom 3")

        secondarySymptoms.add("Secondary Symptom 1")
        secondarySymptoms.add("Secondary Symptom 2")
        secondarySymptoms.add("Secondary Symptom 3")

        return inflater!!.inflate(R.layout.detail_view, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        primarySymptomListView  = getView()!!.findViewById<ListView>(R.id.primary_symptoms_list) as ListView
        secondarySymptomListView  = getView()!!.findViewById<ListView>(R.id.secondary_symptoms_list) as ListView
        detailImageView = getView()!!.findViewById<ImageView>(R.id.detail_image_view) as ImageView
        detailTitleView = getView()!!.findViewById<TextView>(R.id.detail_title_view) as TextView

        detailTitleView.text = getString(R.string.details_example_title)
        detailImageView.setImageResource(R.drawable.tibialis_anterior)
        primarySymptomListView.adapter = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, primarySymptoms)
        secondarySymptomListView.adapter = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, secondarySymptoms)
    }
}