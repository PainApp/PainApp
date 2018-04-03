package xyz.painapp.pocketdoc.activities

import android.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import xyz.painapp.pocketdoc.R
import android.net.http.HttpResponseCache
import org.json.JSONObject
import xyz.painapp.pocketdoc.entities.DownloadDataTask
import xyz.painapp.pocketdoc.entities.HTTPUrlMethod
import xyz.painapp.pocketdoc.fragments.LoadingFragment
import xyz.painapp.pocketdoc.fragments.MainFragment
import java.io.*
import java.net.URL


class MainActivity : AppCompatActivity() {
    //private lateinit var actionListView : ListView
    private lateinit var fManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
     //   actionListView = findViewById(R.id.main_action_list)

/*        builder = android.app.AlertDialog.Builder(this)

        builder.setMessage(R.string.error_connect_internet)
                .setTitle(R.string.error_dialog_title)*/
        fManager = fragmentManager


       DownloadConnectInfo().execute(HTTPUrlMethod(URL(HTTPUrlMethod.BASE_URL), HTTPUrlMethod.GET, null))

    }



    override fun onStop() {
        super.onStop()
        HttpResponseCache.getInstalled()?.flush()
    }


    inner class DownloadConnectInfo: DownloadDataTask() {
        override fun onPreExecute() {
            val transaction = fManager.beginTransaction()
            val newFragment = LoadingFragment.newInstance(internetError = true)
            transaction.replace(R.id.main_fragment_container, newFragment)
            transaction.commit()
        }

        override fun onPostExecute(result: JSONObject?) {
            if (result!!.has(HTTPUrlMethod.RESPONSE_CODE_STR) && result[HTTPUrlMethod.RESPONSE_CODE_STR] != 200) {
                fManager.beginTransaction().replace(R.id.main_fragment_container, LoadingFragment.newInstance(errorMessage = getString(R.string.error_connect_internet), internetError = true)).commit()
            } else {
                fManager.beginTransaction().replace(R.id.main_fragment_container, MainFragment.newInstance()).commit()
                try {
                    val httpCacheDir = File(cacheDir, "http")
                    val httpCacheSize = (10 * 1024 * 1024).toLong() // 10 MiB
                    HttpResponseCache.install(httpCacheDir, httpCacheSize)
                } catch (e: IOException) {
                    Log.e("Error", "HTTP response cache installation failed:" + e)
                    e.printStackTrace()
                }
            }
        }
    }
}
