package xyz.painapp.pocketdoc.activities

import android.app.Fragment
import android.app.FragmentManager
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import org.json.JSONObject
import xyz.painapp.pocketdoc.R
import xyz.painapp.pocketdoc.entities.BodyRegion
import xyz.painapp.pocketdoc.entities.DownloadDataTask
import xyz.painapp.pocketdoc.entities.HTTPUrlMethod
import xyz.painapp.pocketdoc.entities.OnTaskCompletedListener
import xyz.painapp.pocketdoc.fragments.LoadingFragment
import xyz.painapp.pocketdoc.fragments.RegionFragment

class RegionActivity : AppCompatActivity(), OnTaskCompletedListener {


    private lateinit var bodyRegion: BodyRegion
    private var fManager: FragmentManager? = null
    private var currentFragment: Fragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_region)

        // Set toolbar
        val toolbar = findViewById<Toolbar>(R.id.main_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        bodyRegion = intent.getParcelableExtra(BodyRegion.BODY_REGION_STR)
        fManager = fragmentManager
        downloadRegionInfo()

    }

    override fun onResume() {
        super.onResume()
        if (currentFragment != null && !(currentFragment!!.isVisible)) {
            fragmentManager!!.beginTransaction().replace(R.id.region_fragment_container, currentFragment).commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.body_activity_options_menu, menu)
        return true
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }

    private fun downloadRegionInfo() {
         DownloadRegionInfoTask(this, fragmentManager).execute(HTTPUrlMethod(
                HTTPUrlMethod.BODY_REGION_URL,
                HTTPUrlMethod.POST,
                bodyRegion.toJSONObject()))
    }

    override fun onTaskCompleted(vararg values: Any?) {
        if (values[0] is JSONObject) {
            bodyRegion = BodyRegion(values[0] as JSONObject)
            if (bodyRegion.specificRegionList.size > 0) {
                fragmentManager.beginTransaction().replace(R.id.region_fragment_container, RegionFragment.newInstance(bodyRegion)).commit()
            } else {
                showSnackBarError(String.format(getString(R.string.no_data), bodyRegion.name))
            }
        } else {
            showSnackBarError(getString(R.string.error_connect_internet))
        }
    }

    private fun showSnackBarError(message: String) {
        val lFragment = fragmentManager.findFragmentByTag(LOADING_FRAGMENT_TAG) as LoadingFragment
        lFragment.setProgressVisible(false)

        val snackbar = Snackbar.make(findViewById(R.id.region_fragment_container), message, Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction(getString(R.string.retry), { _ ->
            run {
                snackbar.dismiss()
                downloadRegionInfo()
            }
        }).show()
    }

    companion object {
        private const val LOADING_FRAGMENT_TAG = "loading_fragment"

        class DownloadRegionInfoTask(override val listener: OnTaskCompletedListener, override val fragmentManager: FragmentManager): DownloadDataTask() {
            override fun onPreExecute() {
                val transaction = fragmentManager.beginTransaction()
                val newFragment = LoadingFragment()
                transaction.replace(R.id.region_fragment_container, newFragment, LOADING_FRAGMENT_TAG)
                transaction.commit()
            }

            override fun onPostExecute(result: JSONObject?) {
                if (!result!!.has(HTTPUrlMethod.RESPONSE_CODE_STR)) {
                    listener.onTaskCompleted(result)
                } else {
                    listener.onTaskCompleted(false)
                }
            }

        }
    }
}
