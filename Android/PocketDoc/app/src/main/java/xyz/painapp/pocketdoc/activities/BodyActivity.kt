package xyz.painapp.pocketdoc.activities

import android.app.Fragment
import android.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.Snackbar
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import org.json.JSONObject
import xyz.painapp.pocketdoc.R
import xyz.painapp.pocketdoc.entities.BodyRegion
import xyz.painapp.pocketdoc.entities.DownloadDataTask
import xyz.painapp.pocketdoc.entities.HTTPUrlMethod
import xyz.painapp.pocketdoc.entities.OnTaskCompleted
import xyz.painapp.pocketdoc.fragments.BodyFragment
import xyz.painapp.pocketdoc.fragments.LoadingFragment

class BodyActivity : AppCompatActivity(), View.OnClickListener, OnTaskCompleted {



    private var fManager: FragmentManager? = null
    private var currentFragment: Fragment? = null
    private lateinit var flipButton: Button
    private var orientation = true
    private lateinit var errorSnackbar: Snackbar
    private var bodyRegionList: ArrayList<BodyRegion>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_body)

        // Set toolbar
        val toolbar = findViewById<Toolbar>(R.id.main_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        flipButton = findViewById(R.id.flip_body_button)

        fManager = fragmentManager
        errorSnackbar = Snackbar.make(findViewById<ConstraintLayout>(R.id.body_fragment_container), getString(R.string.error_connect_internet), Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(R.string.retry), {
                    _ ->
                    run {
                        errorSnackbar.dismiss()
                        testConnection()
                    }
                })


        flipButton.setOnClickListener(this)
    }

    private fun testConnection() {
        DownloadBodyInfoTask(this, fManager!!, errorSnackbar, orientation).execute(HTTPUrlMethod(HTTPUrlMethod.BODY_REGION_URL, HTTPUrlMethod.GET,null))
    }

    override fun onPostResume() {
        super.onPostResume()
        fManager = fragmentManager
        DownloadBodyInfoTask(this, fManager!!, errorSnackbar, orientation).execute(HTTPUrlMethod(HTTPUrlMethod.BODY_REGION_URL, HTTPUrlMethod.GET,null))
    }

    override fun onResume() {
        super.onResume()
        if (currentFragment != null) {
            fManager!!.beginTransaction().replace(R.id.body_fragment_container, currentFragment).commit()
        }

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

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.flip_body_button -> this.flipBody()
        }
    }

    // TODO fix implementation of flipBody
    private fun flipBody() {
        orientation = !orientation
        fManager!!.beginTransaction().replace(R.id.body_fragment_container, BodyFragment.newInstance(bodyRegionList!!, orientation)).commit()
    }

    override fun onTaskCompleted(vararg values: Any?) {
        if (values[0] is ArrayList<*>) {
            val mList = values[0] as ArrayList<*>
            bodyRegionList = mList.filterIsInstance<BodyRegion>() as ArrayList<BodyRegion>
        }
    }

    companion object {

        private const val LOADING_FRAGMENT_TAG = "loading_fragment"
        const val BODY_FRAGMENT_TAG = "body_fragment"

        class DownloadBodyInfoTask(private val listener: OnTaskCompleted, val fragmentManager: FragmentManager, private val errorSnackbar: Snackbar, private val orientation: Boolean) : DownloadDataTask() {
            override fun onPreExecute() {
                val transaction = fragmentManager.beginTransaction()
                val newFragment = LoadingFragment()
                transaction.replace(R.id.body_fragment_container, newFragment, LOADING_FRAGMENT_TAG)
                transaction.commit()
            }

            override fun onPostExecute(result: JSONObject?) {
                val transaction = fragmentManager.beginTransaction()
                if (!result!!.has(HTTPUrlMethod.RESPONSE_CODE_STR)) {
                    val bodyRegionList = BodyRegion.fromJSONArray(result.getJSONArray(BodyRegion.BODY_REGIONS_STR))
                    listener.onTaskCompleted(bodyRegionList)
                    transaction.replace(R.id.body_fragment_container, BodyFragment.newInstance(bodyRegionList, orientation), BODY_FRAGMENT_TAG).commit()
                } else {
                    val lFragment = fragmentManager.findFragmentByTag(LOADING_FRAGMENT_TAG) as LoadingFragment
                    lFragment.setProgressVisible(false)
                    errorSnackbar.show()
                }
            }
    }

/*
    class DownloadRegionInfoTask: DownloadDataTask() {
        override fun onPreExecute() {
            val transaction = fManager!!.beginTransaction()
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
        }*/
    }




}
