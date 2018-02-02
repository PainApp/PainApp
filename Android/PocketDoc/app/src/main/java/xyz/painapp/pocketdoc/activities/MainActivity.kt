package xyz.painapp.pocketdoc.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import xyz.painapp.pocketdoc.R
import java.net.InetAddress

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    private lateinit var actionListView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        actionListView = findViewById(R.id.main_action_list)
        actionListView.adapter = ArrayAdapter.createFromResource(this, R.array.main_action_list, R.layout.main_action_list_item)
        actionListView.onItemClickListener = this

        if (!isInternetAvailable()) {
            val builder = android.app.AlertDialog.Builder(this)

            builder.setMessage(R.string.error_connect_internet)
                    .setTitle(R.string.error_dialog_title)

            builder.setPositiveButton(R.string.ok) { _, _ ->
                startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
            }

            builder.create().show()
        }

    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val item = actionListView.getItemAtPosition(position) as String

        // TODO add in handlers for other activities
        val intent : Intent? =
                when (item) {
                    "start" -> Intent(this, BodyActivity::class.java)
                    "debug" -> Intent(this, DebugActivity::class.java)
                    else -> null
                }

        if (intent != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, "That action isn't supported yet!", Toast.LENGTH_SHORT).show()
        }
    }

    fun isInternetAvailable(): Boolean {
        try {
            val ipAddr = InetAddress.getByName("google.com")
            //You can replace it with your name
            return !ipAddr.equals("")

        } catch (e: Exception) {
            return false
        }

    }
}
