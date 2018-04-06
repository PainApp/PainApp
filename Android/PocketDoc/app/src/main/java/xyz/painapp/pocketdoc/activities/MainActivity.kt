package xyz.painapp.pocketdoc.activities

import android.app.FragmentManager
import android.content.Intent
import android.net.http.HttpResponseCache
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import org.json.JSONObject
import xyz.painapp.pocketdoc.R
import xyz.painapp.pocketdoc.entities.DownloadDataTask
import xyz.painapp.pocketdoc.entities.HTTPUrlMethod
import xyz.painapp.pocketdoc.entities.OnTaskCompletedListener
import xyz.painapp.pocketdoc.fragments.MainFragment
import java.io.File
import java.io.IOException
import java.net.URL


class MainActivity : AppCompatActivity(), MainFragment.OnMainActionSelectedListener, OnTaskCompletedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
        DownloadConnectInfo(this, fragmentManager).execute(HTTPUrlMethod(URL(HTTPUrlMethod.BASE_URL), HTTPUrlMethod.GET, null))
    }

    override fun onTaskCompleted(vararg values: Any?) {
        if (values[0] is Boolean) {
            val error = values[0] as Boolean
            val mainFragment = fragmentManager.findFragmentByTag(FRAGMENT_TAG) as MainFragment

            if (error) {
                mainFragment.setActionListVisibility(false)
                val snackbar = Snackbar.make(findViewById<ConstraintLayout>(R.id.main_fragment_container), getString(R.string.error_connect_internet), Snackbar.LENGTH_INDEFINITE)
                snackbar.setAction(getString(R.string.retry), {
                    _ ->
                    run {
                        snackbar.dismiss()
                        testConnection()
                    }
                }).show()
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


    companion object {
        const val FRAGMENT_TAG = "MAIN_FRAGMENT"
        class DownloadConnectInfo(override val listener: OnTaskCompletedListener, override val fragmentManager: FragmentManager) : DownloadDataTask() {

            override fun onPreExecute() {
                val mainFragment = MainFragment.newInstance(progressBarVisible = true)
                fragmentManager.beginTransaction().add(R.id.main_fragment_container, mainFragment, FRAGMENT_TAG).commit()
            }

            override fun onPostExecute(result: JSONObject?) {
                if (result!!.has(HTTPUrlMethod.RESPONSE_CODE_STR) && result[HTTPUrlMethod.RESPONSE_CODE_STR] != 200) {
                    listener.onTaskCompleted(true)
                } else {
                    listener.onTaskCompleted(false)
                }
            }
        }
    }

}
