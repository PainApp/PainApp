package xyz.painapp.pocketdoc.activities

import android.app.FragmentManager
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import xyz.painapp.pocketdoc.R
import android.net.http.HttpResponseCache
import android.support.constraint.ConstraintLayout
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Toast
import org.json.JSONObject
import xyz.painapp.pocketdoc.entities.DownloadDataTask
import xyz.painapp.pocketdoc.entities.HTTPUrlMethod
import xyz.painapp.pocketdoc.fragments.LoadingFragment
import xyz.painapp.pocketdoc.fragments.MainFragment
import java.io.*
import java.net.URL


class MainActivity : AppCompatActivity(), MainFragment.OnMainActionSelectedListener {


    //private lateinit var actionListView : ListView
    private lateinit var fManager: FragmentManager
    private lateinit var errorSnackbar: Snackbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
     //   actionListView = findViewById(R.id.main_action_list)

/*        builder = android.app.AlertDialog.Builder(this)

        builder.setMessage(R.string.error_connect_internet)
                .setTitle(R.string.error_dialog_title)*/
        fManager = fragmentManager
        errorSnackbar = Snackbar.make(findViewById<ConstraintLayout>(R.id.main_fragment_container), getString(R.string.error_connect_internet), Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(R.string.retry), {
                    _ ->
                    run {
                        errorSnackbar.dismiss()
                        testConnection()
                    }
                })
        fragmentManager.beginTransaction().replace(R.id.main_fragment_container, MainFragment.newInstance()).commit()
        testConnection()
    }

    override fun onMainActionSelected(action: String) {
        val intent: Intent? =
        when (action) {
            "start" -> Intent(this, BodyActivity::class.java)
            "debug" -> Intent(this, DebugActivity::class.java)
            else -> null
        }

        if (intent != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, "This action is not supported yet!", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onStop() {
        super.onStop()
        HttpResponseCache.getInstalled()?.flush()
    }

    private fun testConnection() {
        DownloadConnectInfo(fManager, errorSnackbar, cacheDir).execute(HTTPUrlMethod(URL(HTTPUrlMethod.BASE_URL), HTTPUrlMethod.GET, null))
    }


    companion object {
        const val FRAGMENT_TAG = "MAIN_FRAGMENT"
        class DownloadConnectInfo(private val fragmentManager: FragmentManager, private val errorSnackbar: Snackbar, private val cacheDir: File) : DownloadDataTask() {

            override fun onPreExecute() {
                fragmentManager.beginTransaction().add(R.id.main_fragment_container, MainFragment.newInstance(), FRAGMENT_TAG).commit()

                val mainFragment = fragmentManager.findFragmentByTag(FRAGMENT_TAG) as MainFragment
                mainFragment.setProgressVisibility(true)
            }

            override fun onPostExecute(result: JSONObject?) {
                if (result!!.has(HTTPUrlMethod.RESPONSE_CODE_STR) && result[HTTPUrlMethod.RESPONSE_CODE_STR] != 200) {
                    errorSnackbar.show()
                } else {
                    mainFragment.setProgressVisibility(false)
                    try {
                        val httpCacheDir = File(cacheDir, "http")
                        val httpCacheSize = (10 * 1024 * 1024).toLong() // 10 MiB
                        HttpResponseCache.install(httpCacheDir, httpCacheSize)
                    } catch (e: IOException) {
                        Log.e("Error", "HTTP response cache installation failed:$e")
                        e.printStackTrace()
                    }
                }
            }
        }
    }

}
