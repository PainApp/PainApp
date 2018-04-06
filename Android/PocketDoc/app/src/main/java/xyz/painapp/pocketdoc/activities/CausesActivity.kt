package xyz.painapp.pocketdoc.activities

import android.app.FragmentManager
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import org.json.JSONObject
import xyz.painapp.pocketdoc.R
import xyz.painapp.pocketdoc.entities.DownloadDataTask
import xyz.painapp.pocketdoc.entities.HTTPUrlMethod
import xyz.painapp.pocketdoc.entities.OnTaskCompletedListener
import xyz.painapp.pocketdoc.entities.SpecificBodyRegion
import xyz.painapp.pocketdoc.fragments.CausesFragment
import xyz.painapp.pocketdoc.fragments.LoadingFragment

class CausesActivity : AppCompatActivity(), OnTaskCompletedListener {


    private var fManager: FragmentManager? = null
    private var specificBodyRegion: SpecificBodyRegion? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_causes)

        // Set toolbar
        val toolbar = findViewById<Toolbar>(R.id.main_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        fManager = fragmentManager

        specificBodyRegion = intent.getParcelableExtra(SpecificBodyRegion.S_REGION_STR)


        downloadCauseInfo()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.body_activity_options_menu, menu)
        return true
    }

    private fun downloadCauseInfo() {
        DownloadCauseInfo(this, fragmentManager).execute(HTTPUrlMethod(
                HTTPUrlMethod.SPECIFIC_REGION_URL,
                HTTPUrlMethod.POST,
                specificBodyRegion!!.toJSONObject()
        ))
    }

    override fun onTaskCompleted(vararg values: Any?) {

        if (values[0] is JSONObject) {
            val specificBodyRegion = SpecificBodyRegion(values[0] as JSONObject, specificBodyRegion!!.bodyRegionId, specificBodyRegion!!.bodyRegionName)

            if (specificBodyRegion.causeList.size > 0) {
                fManager!!.beginTransaction().replace(R.id.causes_fragment_container, CausesFragment.newInstance(specificBodyRegion)).commit()
            } else {
               showSnackBarError(String.format(getString(R.string.no_data), specificBodyRegion.getFullName()))
            }
        } else {
            showSnackBarError(getString(R.string.error_connect_internet))
        }
    }

    private fun showSnackBarError(message: String) {
        val lFragment = fragmentManager.findFragmentByTag(LOADING_FRAGMENT_TAG) as LoadingFragment
        lFragment.setProgressVisible(false)
        val snackbar = Snackbar.make(findViewById(R.id.causes_fragment_container), message, Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction(getString(R.string.retry), { _ ->
            run {
                snackbar.dismiss()
                downloadCauseInfo()
            }
        }).show()
    }

    companion object {
        private const val LOADING_FRAGMENT_TAG = "loading_fragment"

        class DownloadCauseInfo(override val listener: OnTaskCompletedListener, override val fragmentManager: FragmentManager): DownloadDataTask() {
            override fun onPreExecute() {
                val transaction = fragmentManager.beginTransaction()
                val newFragment = LoadingFragment()
                transaction.replace(R.id.causes_fragment_container, newFragment, LOADING_FRAGMENT_TAG)
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
