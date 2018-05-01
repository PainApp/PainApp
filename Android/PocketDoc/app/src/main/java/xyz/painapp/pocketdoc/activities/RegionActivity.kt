package xyz.painapp.pocketdoc.activities

import android.app.FragmentManager
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import org.json.JSONObject
import xyz.painapp.pocketdoc.R
import xyz.painapp.pocketdoc.entities.*
import xyz.painapp.pocketdoc.fragments.*

class RegionActivity : AppCompatActivity(), OnTaskCompletedListener, RegionFragment.OnFragmentInteractionListener, HelpDialogInteractionListener {


    private var bodyRegion: BodyRegion? = null
    private var fManager: FragmentManager? = null
    private val helpDialog = HelpDialogFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_region)

        // Set toolbar
        val toolbar = findViewById<Toolbar>(R.id.main_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        bodyRegion = intent.getParcelableExtra(BodyRegion.BODY_REGION_STR)


        if (bodyRegion == null) {
            bodyRegion = savedInstanceState?.getParcelable(BodyRegion.BODY_REGION_STR)
        }

        fManager = fragmentManager
        downloadRegionInfo()
    }


    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        outState?.run {
            putParcelable(BodyRegion.BODY_REGION_STR, bodyRegion)
        }
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item!!.itemId) {
            R.id.help_btn -> helpDialog.show(fragmentManager, HelpDialogFragment.HELP_DIALOG_TAG)
            R.id.refresh_btn -> downloadRegionInfo()
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
        return true
    }

    private fun downloadRegionInfo() {
         DownloadRegionInfoTask(this, fragmentManager).execute(HTTPUrlMethod(
                HTTPUrlMethod.BODY_REGION_URL,
                HTTPUrlMethod.POST,
                bodyRegion?.toJSONObject()))
    }

    override fun onRegionClicked(sRegion: SpecificBodyRegion) {
        val intent = Intent(this, CausesActivity::class.java)
        intent.putExtra(SpecificBodyRegion.S_REGION_STR, sRegion)
        startActivity(intent)
    }

    override fun onTaskCompleted(vararg values: Any?) {
        if (values[0] is JSONObject) {
            bodyRegion = BodyRegion(values[0] as JSONObject)
            if (bodyRegion!!.specificRegionList.size > 0) {
                fragmentManager.beginTransaction().replace(R.id.region_fragment_container, RegionFragment.newInstance(bodyRegion!!)).commit()
            } else {
                Toast.makeText(this, String.format(getString(R.string.no_data), bodyRegion!!.name), Toast.LENGTH_SHORT).show()
                this.onBackPressed()
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

    override fun openHelpFragment() {
        fragmentManager.beginTransaction()
                .replace(R.id.region_fragment_container, HelpFragment.newInstance())
                .addToBackStack(HelpDialogFragment.HELP_FRAGMENT_TAG)
                .commit()
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
