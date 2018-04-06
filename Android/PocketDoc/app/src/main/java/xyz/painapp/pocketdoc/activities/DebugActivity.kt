package xyz.painapp.pocketdoc.activities

import android.os.Bundle
import android.os.Debug
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_debug.*
import org.json.JSONObject
import xyz.painapp.pocketdoc.R
import xyz.painapp.pocketdoc.entities.DownloadDataTask
import xyz.painapp.pocketdoc.entities.HTTPUrlMethod
import java.net.URL
import android.widget.ArrayAdapter
import java.lang.ref.WeakReference


/**
 * DebugActivity.kt
 * This activity is used to debug web server requests
 */
class DebugActivity: AppCompatActivity(), AdapterView.OnItemSelectedListener {


    private lateinit var debugText: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var debugButton: Button
    private lateinit var urlEditText: EditText
    private lateinit var methodSpinner: Spinner
    private lateinit var methodSpinnerVal: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debug)

        debugButton = findViewById(R.id.debug_button)
        debugText = findViewById(R.id.debug_textView)
        progressBar = findViewById(R.id.debug_progressBar)
        urlEditText = findViewById(R.id.url_editText)
        methodSpinner = findViewById(R.id.method_spinner)

        val adapter = ArrayAdapter.createFromResource(this, R.array.method_spinner_list,
                android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        methodSpinner.adapter = adapter
        methodSpinner.onItemSelectedListener = this

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


        debugButton.setOnClickListener { _ ->

            if (urlEditText.text != null && urlEditText.text.toString() != "" && methodSpinnerVal != "") {
         //       DebugDownloadDataTask(this).execute(HTTPUrlMethod(URL(urlEditText.text.toString()), methodSpinnerVal, dataList = dataList))
            } else {
                debug_textView.text = getString(R.string.debug_errStr)
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        methodSpinnerVal = methodSpinner.selectedItem.toString()
    }
    override fun onNothingSelected(parent: AdapterView<*>?) {
        methodSpinnerVal = ""
    }

    companion object { /*class DebugDownloadDataTask(context: DebugActivity) : DownloadDataTask() {
            private var debugActivity:  WeakReference<DebugActivity> = WeakReference(context)

            override fun onPreExecute() {
                debugActivity.get()!!.progressBar.visibility = View.VISIBLE
            }
    
            override fun onPostExecute(result: JSONObject?) {
                debugActivity.get()!!.progressBar.visibility = View.INVISIBLE
                debugActivity.get()!!.debugText.text = result.toString()

                if (debugActivity.get() == null) {
                    return
                }
            }
    
        }*/
    }
}