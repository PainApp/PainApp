package xyz.painapp.pocketdoc.activities

import android.app.Fragment
import android.app.FragmentManager
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import org.json.JSONObject
import xyz.painapp.pocketdoc.R
import xyz.painapp.pocketdoc.entities.BodyRegion
import xyz.painapp.pocketdoc.entities.DownloadDataTask
import xyz.painapp.pocketdoc.entities.HTTPUrlMethod
import xyz.painapp.pocketdoc.fragments.BodyFragment
import xyz.painapp.pocketdoc.fragments.LoadingFragment

class BodyActivity : AppCompatActivity(){


    private var fManager: FragmentManager? = null
    private var currentFragment: Fragment? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_body)

        // Set toolbar
        val toolbar = findViewById<Toolbar>(R.id.main_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        fManager = fragmentManager
        DownloadBodyInfoTask().execute(HTTPUrlMethod(HTTPUrlMethod.BODY_REGION_URL, HTTPUrlMethod.GET,null))

    }

    override fun onPostResume() {
        super.onPostResume()
        fManager = fragmentManager
      //  Log.i("HERE", "HERE")
        DownloadBodyInfoTask().execute(HTTPUrlMethod(HTTPUrlMethod.BODY_REGION_URL, HTTPUrlMethod.GET,null))
    }

    override fun onResume() {
        super.onResume()
        if (currentFragment != null) {
            fManager!!.beginTransaction().replace(R.id.body_fragment_container, currentFragment).commit()
        }
    //    DownloadBodyInfoTask().execute(HTTPUrlMethod(HTTPUrlMethod.BODY_REGION_URL, HTTPUrlMethod.GET,null))

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.body_activity_options_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item!!.itemId) {
            R.id.search_btn -> Toast.makeText(this, "You clicked search", Toast.LENGTH_SHORT).show() // TODO add search handler
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }

        return super.onOptionsItemSelected(item)
    }


    inner class DownloadBodyInfoTask: DownloadDataTask() {
        override fun onPreExecute() {
            Log.i("HERE", "Here")
            val transaction = fManager!!.beginTransaction()
            val newFragment = LoadingFragment()
            transaction.replace(R.id.body_fragment_container, newFragment)
            transaction.commit()
        }

        override fun onPostExecute(result: JSONObject?) {
            val transaction = fManager!!.beginTransaction()
            Log.i("Results:", result.toString())
            currentFragment = if (!result!!.has(HTTPUrlMethod.RESPONSE_CODE_STR)) {
                BodyFragment.newInstance(BodyRegion.fromJSONArray(result.getJSONArray(BodyRegion.BODY_REGIONS_STR)))
            } else {
                LoadingFragment.newInstance(getString(R.string.error_server_message))
            }


            transaction.replace(R.id.body_fragment_container, currentFragment!!)
            transaction.commit()
        }

    }

    inner class DownloadRegionInfoTask: DownloadDataTask() {
        override fun onPreExecute() {
            val transaction = fManager!!.beginTransaction()
            Log.i("HERE", "Here2")

            val newFragment = LoadingFragment()
            transaction.replace(R.id.body_fragment_container, newFragment)
            transaction.commit()
        }

        override fun onPostExecute(result: JSONObject?) {
            if (!result!!.has(HTTPUrlMethod.RESPONSE_CODE_STR)) {
                val intent = Intent(applicationContext, RegionActivity::class.java)
                val region = BodyRegion(result)
                if (region.specificRegionList.size > 0) {
                    intent.putExtra(BodyRegion.BODY_REGION_STR, BodyRegion(result))
                    applicationContext.startActivity(intent)
                } else {
                    if (currentFragment != null) {
                        fManager!!.beginTransaction().replace(R.id.body_fragment_container, currentFragment).commit()
                    }
                    Toast.makeText(applicationContext, getString(R.string.no_data, region.name), Toast.LENGTH_SHORT).show()
                }
            } else {
                fManager!!.beginTransaction().replace(R.id.region_fragment_container, LoadingFragment.newInstance(getString(R.string.error_server_message))).commit()
            }
        }
    }



}
