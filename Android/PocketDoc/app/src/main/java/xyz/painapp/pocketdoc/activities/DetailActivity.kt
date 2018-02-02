package xyz.painapp.pocketdoc.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import xyz.painapp.pocketdoc.R

class DetailActivity : AppCompatActivity() {
    private var primarySymptoms: ArrayList<String> = ArrayList()
    private var secondarySymptoms: ArrayList<String> = ArrayList()
    private lateinit var detailImageView: ImageView
    private lateinit var primarySymptomListView: ListView
    private lateinit var secondarySymptomListView: ListView
    private lateinit var detailTitleView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Set toolbar
        val toolbar = findViewById<Toolbar>(R.id.main_toolbar)
        setSupportActionBar(toolbar)


        // TODO set title, images and symptoms based on actual data from bundle arg
        primarySymptoms = ArrayList()
        secondarySymptoms = ArrayList()
        primarySymptoms.add("Primary Symptom 1")
        primarySymptoms.add("Primary Symptom 2")
        primarySymptoms.add("Primary Symptom 3")

        secondarySymptoms.add("Secondary Symptom 1")
        secondarySymptoms.add("Secondary Symptom 2")
        secondarySymptoms.add("Secondary Symptom 3")

        primarySymptomListView  = findViewById(R.id.primary_symptoms_list)
        secondarySymptomListView  = findViewById(R.id.secondary_symptoms_list)
        detailImageView = findViewById(R.id.detail_image_view)
        detailTitleView = findViewById(R.id.detail_title_view)

        detailTitleView.text = getString(R.string.details_example_title)
        detailImageView.setImageResource(R.drawable.tibialis_anterior)
        primarySymptomListView.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, primarySymptoms)
        secondarySymptomListView.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, secondarySymptoms)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.body_activity_options_menu, menu)
        return true
    }
}
