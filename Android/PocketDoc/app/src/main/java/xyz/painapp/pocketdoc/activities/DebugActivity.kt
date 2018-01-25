package xyz.painapp.pocketdoc.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import org.json.JSONObject
import xyz.painapp.pocketdoc.R
import xyz.painapp.pocketdoc.entities.DownloadDataTask
import xyz.painapp.pocketdoc.entities.HTTPUrlMethod
import java.net.URL

/**
 * Created by Keyur on 1/24/2018.
 */
class DebugActivity: AppCompatActivity() {
    private lateinit var debugText: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var debugButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debug)

        debugButton = findViewById(R.id.debug_button)
        debugText = findViewById(R.id.debug_textView)
        progressBar = findViewById(R.id.debug_progressBar)
        val dataList = ArrayList<String>(1)
        dataList.add("{ \n" +
                "  \"_id\": \"bd8fa389-3a7a-4478-8845-e36a02de1b7b\",\n" +
                "  \"title\": \"Fried Eggs\",\n" +
                "  \"ingredients\": [\n" +
                "    {\n" +
                "      \"name\": \"Egg\",\n" +
                "      \"amount\": \"2 eggs\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"Olive Oil\",\n" +
                "      \"amount\": \"2 tbsp\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"steps\": [\n" +
                "    \"First, heat a non-stick pan on medium-high until hot\",\n" +
                "    \"Add the oil to the pan and allow oil to warm; it is ready the oil immediately sizzles upon contact with a drop of water.\",\n" +
                "    \"Crack the egg and place the egg and yolk in a small prep bowl; do not crack the yolk!\",\n" +
                "    \"Gently pour the egg from the bowl onto the oil\",    \n" +
                "    \"Wait for egg white to turn bubbly and completely opaque (approx 2 min)\",\n" +
                "    \"Using a spatula, flip the egg onto its uncooked side until it is completely cooked (approx 2 min)\",\n" +
                "    \"Remove from oil and plate\",\n" +
                "    \"Repeat for second egg\"\n" +
                "  ],\n" +
                "  \"comments\": []\n" +
                "}\n")


        debugButton.setOnClickListener {v ->
            DebugDownloadDataTask().execute(HTTPUrlMethod(URL("http://10.0.2.2:3000/recipes"), "POST", dataList = dataList ))
            }
    }

    inner class DebugDownloadDataTask: DownloadDataTask() {
        override fun onPreExecute() {
            progressBar.visibility = View.VISIBLE
        }

        override fun onPostExecute(result: JSONObject?) {
            progressBar.visibility = View.INVISIBLE
            debugText.text = result.toString()
        }

    }
}