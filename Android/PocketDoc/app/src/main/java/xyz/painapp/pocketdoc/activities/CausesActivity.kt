package xyz.painapp.pocketdoc.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import xyz.painapp.pocketdoc.R
import xyz.painapp.pocketdoc.adapters.CausesViewAdapter
import xyz.painapp.pocketdoc.entities.Cause

class CausesActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    private var gridView: GridView? = null
    private var causes: ArrayList<Cause> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_causes)

        // Set toolbar
        val toolbar = findViewById<Toolbar>(R.id.main_toolbar)
        setSupportActionBar(toolbar)

        //TODO get bundle
        causes = ArrayList()
        causes.add(Cause("Cause 1", 3))
        causes.add(Cause("Cause 2", 2))
        causes.add(Cause("Cause 3", 1))
        causes.add(Cause("Cause 4", 0))


        gridView = findViewById(R.id.cause_grid_view)

        gridView!!.adapter = CausesViewAdapter(this, causes)

        gridView!!.onItemClickListener = this
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.body_activity_options_menu, menu)
        return true
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val causeStr = causes[position].cause
        val intent = Intent(this, DetailActivity::class.java)

        intent.putExtra("Cause", causeStr)

        startActivity(intent)

    }

}
