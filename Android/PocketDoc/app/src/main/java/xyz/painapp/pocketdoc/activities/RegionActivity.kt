package xyz.painapp.pocketdoc.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import xyz.painapp.pocketdoc.R

class RegionActivity : AppCompatActivity(), View.OnTouchListener, AdapterView.OnItemClickListener {

    private var region = ""
    private var symptomsListView: ListView? = null
    private var regionImageView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_region)

        // Set toolbar
        val toolbar = findViewById<Toolbar>(R.id.main_toolbar)
        setSupportActionBar(toolbar)


        region = intent.getStringExtra("Region")

        symptomsListView = findViewById(R.id.region_symptoms_list_view)
        regionImageView = findViewById(R.id.region_image_view)

        // TODO implement dynamic data based on region argument
        symptomsListView!!.adapter = ArrayAdapter.createFromResource(this, R.array.sample_symptoms_list, R.layout.symptom_list_item)
        regionImageView!!.setImageResource(R.drawable.calf_region)

        symptomsListView!!.onItemClickListener = this
        regionImageView!!.setOnTouchListener(this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.body_activity_options_menu, menu)
        return true
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when (v) {
            regionImageView -> return imgButtonTouch(v, event)
            else -> v!!.performClick()
        }
        return true
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val symptom = symptomsListView!!.getItemAtPosition(position) as String
        val intent = Intent(this, CausesActivity::class.java)

        intent.putExtra("Symptom", symptom)
        startActivity(intent)

    }


    //TODO get actual data passed around
    private fun imgButtonTouch(v: View?, event: MotionEvent?): Boolean {
        val x : Int = event!!.x.toInt()
        val y : Int = event.y.toInt()
        val intent = Intent(this, CausesActivity::class.java)

        val symptom = getSymptom(x, y)


        intent.putExtra("Symptom", symptom)
        startActivity(intent)

        v!!.performClick()

        return true
    }

    private fun getSymptom(x: Int, y: Int): String {
        return "test"
    }

}
