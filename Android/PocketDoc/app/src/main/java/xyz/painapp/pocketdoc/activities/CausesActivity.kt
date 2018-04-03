package xyz.painapp.pocketdoc.activities

import android.app.Fragment
import android.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.widget.GridLayout
import org.json.JSONObject
import xyz.painapp.pocketdoc.R
import xyz.painapp.pocketdoc.entities.Cause
import xyz.painapp.pocketdoc.entities.DownloadDataTask
import xyz.painapp.pocketdoc.entities.HTTPUrlMethod
import xyz.painapp.pocketdoc.entities.SpecificBodyRegion
import xyz.painapp.pocketdoc.fragments.CausesFragment
import xyz.painapp.pocketdoc.fragments.LoadingFragment

class CausesActivity : AppCompatActivity() {
    private var causes: ArrayList<Cause> = ArrayList()
    private var fManager: FragmentManager? = null
    private lateinit var specificBodyRegion: SpecificBodyRegion
    private var currentFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_causes)

        // Set toolbar
        val toolbar = findViewById<Toolbar>(R.id.main_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        fManager = fragmentManager

        specificBodyRegion = intent.getParcelableExtra(SpecificBodyRegion.S_REGION_STR)
        currentFragment = CausesFragment.newInstance(specificBodyRegion)

        fragmentManager!!.beginTransaction().replace(R.id.causes_fragment_container, currentFragment).commit()

/*
        DownloadCauseInfo().execute(HTTPUrlMethod(
                HTTPUrlMethod.SPECIFIC_REGION_URL,
                HTTPUrlMethod.POST,
                specificBodyRegion!!.toJSONObject()
        ))*/
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.body_activity_options_menu, menu)
        return true
    }
    override fun onBackPressed() {
        finish()
    }

    inner class DownloadCauseInfo: DownloadDataTask() {
        override fun onPreExecute() {
            val transaction = fManager!!.beginTransaction()
            val newFragment = LoadingFragment()
            transaction.replace(R.id.causes_fragment_container, newFragment)
            transaction.commit()
        }

        override fun onPostExecute(result: JSONObject?) {
            val transaction = fManager!!.beginTransaction()
            Log.i("s_region_result", result.toString())
            val newFragment: Fragment? = if (!result!!.has(HTTPUrlMethod.RESPONSE_CODE_STR)) {
                CausesFragment.newInstance(SpecificBodyRegion(result, specificBodyRegion.bodyRegionId, specificBodyRegion.bodyRegionName))
            } else {
                LoadingFragment.newInstance(getString(R.string.error_server_message))
            }


            transaction.replace(R.id.causes_fragment_container, newFragment!!)
            transaction.commit()
        }
    }
}
